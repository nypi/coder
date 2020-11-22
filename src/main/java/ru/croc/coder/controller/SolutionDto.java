package ru.croc.coder.controller;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ru.croc.coder.domain.ProcessStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SolutionDto {

	private Long id;

	private Long problemId;

	@JsonProperty("submitTime")
	private LocalDateTime time;

	private Boolean passed;

	private ProcessStatus checkStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProblemId() {
		return problemId;
	}

	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}

	public ProcessStatus getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(ProcessStatus checkStatus) {
		this.checkStatus = checkStatus;
	}
}
