package ru.croc.coder.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "Problem")
@Entity
public class Problem {

	@Id
	@GeneratedValue
	private Long id;

	@JoinColumn(nullable = false)
	@ManyToOne
	private User author;

	@Enumerated(EnumType.STRING)
	@Column(name = "level", nullable = false)
	private ProblemLevel level;

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "description", nullable = false)
	@Lob
	private String description;

	@Embedded
	private Code template;

	private Integer maxAttempts;

	public Long getId() {
		return id;
	}

	public Problem setId(Long id) {
		this.id = id;
		return this;
	}

	public User getAuthor() {
		return author;
	}

	public Problem setAuthor(User author) {
		this.author = author;
		return this;
	}

	public ProblemLevel getLevel() {
		return level;
	}

	public Problem setLevel(ProblemLevel level) {
		this.level = level;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Problem setDescription(String description) {
		this.description = description;
		return this;
	}

	public Code getTemplate() {
		return template;
	}

	public Problem setTemplate(Code template) {
		this.template = template;
		return this;
	}

	public Integer getMaxAttempts() {
		return maxAttempts;
	}

	public Problem setMaxAttempts(Integer maxAttempts) {
		this.maxAttempts = maxAttempts;
		return this;
	}
}
