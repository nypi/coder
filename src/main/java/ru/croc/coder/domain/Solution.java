package ru.croc.coder.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "Solution")
@Entity
public class Solution {

	@Id
	@GeneratedValue
	private Long id;

	@JoinColumn(nullable = false)
	@ManyToOne
	private User author;

	@JoinColumn(nullable = false)
	@ManyToOne
	private Problem problem;

	@Column(name = "time", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime time;

	private Code code;

	@Column(nullable = false)
	private Boolean passed = false;

	public Long getId() {
		return id;
	}

	public Solution setId(Long id) {
		this.id = id;
		return this;
	}

	public User getAuthor() {
		return author;
	}

	public Solution setAuthor(User author) {
		this.author = author;
		return this;
	}

	public Problem getProblem() {
		return problem;
	}

	public Solution setProblem(Problem problem) {
		this.problem = problem;
		return this;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public Solution setTime(LocalDateTime time) {
		this.time = time;
		return this;
	}

	public Code getCode() {
		return code;
	}

	public Solution setCode(Code code) {
		this.code = code;
		return this;
	}

	public Boolean getPassed() {
		return passed;
	}

	public Solution setPassed(Boolean passed) {
		this.passed = passed;
		return this;
	}
}
