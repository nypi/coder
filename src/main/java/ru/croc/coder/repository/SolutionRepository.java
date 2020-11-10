package ru.croc.coder.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import ru.croc.coder.domain.Problem;
import ru.croc.coder.domain.ProcessStatus;
import ru.croc.coder.domain.Solution;
import ru.croc.coder.domain.User;

public interface SolutionRepository extends CrudRepository<Solution, Long> {

	Optional<Solution> findTopByCheckStatus(ProcessStatus checkStatus);

	default Optional<Solution> findAnyQueued() {
		return findTopByCheckStatus(ProcessStatus.QUEUED);
	}

	long countByAuthorAndProblem(User author, Problem problem);

	@Override
	@RestResource(exported = false)
	<S extends Solution> S save(S entity);
}
