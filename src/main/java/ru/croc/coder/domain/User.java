package ru.croc.coder.domain;

import javax.persistence.*;

@Table(name = "\"User\"")
@Entity(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false, length = 12)
	private String password;

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	private String firstName;

	private String lastName;

	private Integer attemptsCount = 0;

	public Long getId() {
		return id;
	}

	public User setId(Long id) {
		this.id = id;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

	public Integer getAttemptsCount() {
		return attemptsCount;
	}

	public User setAttemptsCount(Integer attemptsCount) {
		this.attemptsCount = attemptsCount;
		return this;
	}
	
}
