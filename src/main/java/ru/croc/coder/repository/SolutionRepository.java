package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import ru.croc.coder.domain.Problem;
import ru.croc.coder.domain.Solution;
import ru.croc.coder.domain.User;

public interface SolutionRepository extends CrudRepository<Solution, Long> {

	long countByAuthorAndProblem(User author, Problem problem);

	@Override
	@RestResource(exported = false)
	<S extends Solution> S save(S entity);
}
