package com.ultimatesoftware.school.domain.subject;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.ultimatesoftware.school.domain.person.Student;

@Entity
@Table(name = "student_subject")
public class StudentSubject {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "student_id", nullable = false, updatable = false)
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "subject_id", nullable = false, updatable = false)
	private Subject subject;

	protected StudentSubject() {
		super();
	}

	public StudentSubject(Student student, Subject subject) {
		this.student = student;
		this.subject = subject;
	}

	public Long getId() {
		return id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		StudentSubject that = (StudentSubject) o;
		return Objects.equals(getId(), that.getId()) && getStudent().equals(that.getStudent()) && getSubject().equals(
				that.getSubject());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getStudent(), getSubject());
	}
}
