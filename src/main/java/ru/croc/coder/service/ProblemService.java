package ru.croc.coder.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import ru.croc.coder.domain.Code;
import ru.croc.coder.domain.Problem;
import ru.croc.coder.domain.ProcessStatus;
import ru.croc.coder.domain.Solution;
import ru.croc.coder.domain.User;
import ru.croc.coder.repository.ProblemRepository;
import ru.croc.coder.repository.SolutionRepository;
import ru.croc.coder.repository.UserRepository;

@Service
public class ProblemService {

	private static final Logger log = LoggerFactory.getLogger(ProblemService.class);

	private static final Random rnd = new Random(System.currentTimeMillis());
	
	private UserRepository userRepository;

	private ProblemRepository problemRepository;

	private SolutionRepository solutionRepository;
	
	private UserContext userContext;

	public ProblemService(UserRepository userRepository, ProblemRepository problemRepository,
			SolutionRepository solutionRepository, UserContext userContext) {
		this.userRepository = userRepository;
		this.problemRepository = problemRepository;
		this.solutionRepository = solutionRepository;
		this.userContext = userContext;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = ProblemConstraintException.class)
	public Solution submit(Long problemId, String code) throws InterruptedException {
		
		User user = userContext.getCurrentUser();
		
		Problem problem = problemRepository.findById(problemId)
				.orElseThrow(NotFoundException::new);
		
		userRepository.save(user.setAttemptsCount(user.getAttemptsCount() + 1));

		if (problem.getMaxAttempts() != null) {
			long attempts = solutionRepository.countByAuthorAndProblem(user, problem);
			if (attempts >= problem.getMaxAttempts())
				throw new ProblemConstraintException("Max attempts exceeded");
		}

		Solution solution = new Solution()
				.setAuthor(user)
				.setProblem(problem)
				.setTime(LocalDateTime.now())
				.setCode(new Code()
						.setText(code)
						.setLanguage(problem.getTemplate().getLanguage()))
				.setCheckStatus(ProcessStatus.QUEUED);

		return solutionRepository.save(solution);
	}

	@Async
	@Scheduled(fixedRate = 1_000, initialDelay = 3_000)
	public void checkSolution() throws InterruptedException {
		log.info("Scheduled check");

		Solution solution = null;
		try {
			Optional<Solution> result = peekNextSolution();
			if (result.isEmpty())
				return;

			solution = result.get();
			boolean passed = runTests(solution);
			solution.setPassed(passed);
		} finally {
			// set COMPLETED
			if (solution != null) {
				solution.setCheckStatus(ProcessStatus.COMPLETED);
				solutionRepository.save(solution);
			}
		}
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public Optional<Solution> peekNextSolution() {
		// find QUEUED
		Optional<Solution> result = solutionRepository.findAnyQueued();
		if (result.isPresent()) {
			// set IN_PROGRESS
			Solution solution = result.get();
			solution.setCheckStatus(ProcessStatus.IN_PROGRESS);
			solutionRepository.save(solution);
		}
		return result;
	}

	private boolean runTests(Solution solution) throws InterruptedException {
		Thread.sleep(15_000);
		return rnd.nextBoolean();
	}
}
