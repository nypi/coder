package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;

import ru.croc.coder.domain.Problem;

public interface ProblemRepository extends CrudRepository<Problem, Long> {
}
