package com.ultimatesoftware.school.rest.person;

import java.util.HashSet;
import java.util.Set;
import com.ultimatesoftware.school.rest.subject.SubjectDto;

public class StudentDto {

	private Long id;
	private String lastName;
	private String firstName;
	private Set<SubjectDto> subjects = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Set<SubjectDto> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<SubjectDto> subjects) {
		this.subjects = subjects;
	}
}
