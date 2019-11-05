package com.ultimatesoftware.school.domain.person;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(final StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	@Transactional(readOnly = true)
	public Optional<Student> getStudent(Long id) {
		return studentRepository.findById(id);
	}


	@Transactional(readOnly = true)
	public Collection<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Transactional
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Transactional
	public void delete(Long id) {
		studentRepository.deleteById(id);
	}
	
}
