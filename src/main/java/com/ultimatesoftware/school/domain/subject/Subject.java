package com.ultimatesoftware.school.domain.subject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.ultimatesoftware.school.domain.person.Student;

@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String name;

	@NotNull
	@OneToMany(mappedBy = "subject", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private Set<StudentSubject> students = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students.stream().map(StudentSubject::getStudent).collect(Collectors.toSet());
	}

	public void setStudents(Set<Student> students) {
		this.students = students.stream().map(s -> new StudentSubject(s, this)).collect(Collectors.toSet());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Subject subject = (Subject) o;
		return Objects.equals(getId(), subject.getId()) && getName().equals(subject.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}
}
