package ru.croc.coder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.croc.coder.domain.Code;
import ru.croc.coder.domain.Problem;
import ru.croc.coder.domain.ProblemLevel;
import ru.croc.coder.domain.ProgrammingLanguage;
import ru.croc.coder.domain.User;
import ru.croc.coder.repository.ProblemRepository;
import ru.croc.coder.repository.UserRepository;

@Component
public class Init implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Init.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProblemRepository problemRepository;

	@Override
	public void run(String[] args) throws Exception {
		log.info("Init application");

		long numUser = userRepository.count();
		log.info("Number of users: {}", numUser);

		if (userRepository.findПожалуйстаByEmailIgnoreCase("episarenko@croc.ru").isEmpty()) {
			log.info("Creating initial user");
			Long userId = createUser("Evgeny", "Pisarenko", "episarenko@croc.ru");
			log.info("Created user id: {}", userId);
			
			userId = createUser("Andrei", "Kogun", "akogun@croc.ru");
			log.info("Created user id: {}", userId);
		}

		User author = userRepository.findПожалуйстаByEmailIgnoreCase("akogun@croc.ru").get();
		Problem problem1 = createEasyJavaProblem(author, "Do a + b");
		log.info("Created problem id: {}", problem1.getId());
		Problem problem2 = createEasyJavaProblem(author, "Do a / b");
		log.info("Created problem id: {}", problem2.getId());
	}

	private Long createUser(String fName, String sName, String email) {
		User user = new User()
				.setFirstName(fName)
				.setLastName(sName)
				.setEmail(email)
				.setPassword("");

		Long userId = userRepository.save(user).getId();
		return userId;
	}

	private Problem createEasyJavaProblem(User author, String description) {
		Problem problem = new Problem()
				.setAuthor(author)
				.setDescription(description)
				.setLevel(ProblemLevel.EASY)
				.setTemplate(new Code()
						.setText("// Write your code here")
						.setLanguage(ProgrammingLanguage.JAVA))
				.setMaxAttempts(3);
		return problemRepository.save(problem);
	}
}
