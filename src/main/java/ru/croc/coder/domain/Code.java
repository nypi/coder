package ru.croc.coder.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;

@Embeddable
public class Code {

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "text", nullable = false)
	@Lob
	private String text;

	@Enumerated(EnumType.STRING)
	@Column(name = "language", nullable = false)
	private ProgrammingLanguage language;

	public String getText() {
		return text;
	}

	public Code setText(String text) {
		this.text = text;
		return this;
	}

	public ProgrammingLanguage getLanguage() {
		return language;
	}

	public Code setLanguage(ProgrammingLanguage language) {
		this.language = language;
		return this;
	}
}
