package ru.croc.coder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.croc.coder.domain.Solution;
import ru.croc.coder.domain.User;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.NotFoundException;
import ru.croc.coder.service.ProblemService;
import ru.croc.coder.service.UserContext;

@RestController
public class SolutionController {

	@Autowired
	private ProblemService problemService;
	
	@Autowired
	private UserContext userContext;

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/auth/{userId}")
	public User auth(@PathVariable Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(NotFoundException::new);
		userContext.setCurrentUser(user);
		return user;
	}

	@PostMapping("/problems/{problemId}/solutions")
	public Solution submit(@PathVariable Long problemId,
			@RequestBody String code) {
		return problemService.submit(problemId, code);
	}
}
