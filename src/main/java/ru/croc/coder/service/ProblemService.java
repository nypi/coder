package ru.croc.coder.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import ru.croc.coder.domain.Code;
import ru.croc.coder.domain.Problem;
import ru.croc.coder.domain.Solution;
import ru.croc.coder.domain.User;
import ru.croc.coder.repository.ProblemRepository;
import ru.croc.coder.repository.SolutionRepository;
import ru.croc.coder.repository.UserRepository;

@Service
public class ProblemService {

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
	public Solution submit(Long problemId, String code) {
		
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
				.setPassed(rnd.nextBoolean());

		return solutionRepository.save(solution);
	}
}
