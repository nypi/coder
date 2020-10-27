package ru.croc.coder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.croc.coder.domain.Solution;
import ru.croc.coder.service.ProblemService;

@RestController
public class SolutionController {

	@Autowired
	private ProblemService problemService;

	@PostMapping("/users/{userId}/problems/{problemId}/solutions")
	public Solution submit(@PathVariable Long userId, @PathVariable Long problemId,
			@RequestBody String code) {
		return problemService.submit(userId, problemId, code);
	}
}
