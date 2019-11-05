package com.ultimatesoftware.school.domain.subject;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubjectService {

	private final SubjectRepository subjectRepository;

	@Autowired
	public SubjectService(final SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}
	
	@Transactional(readOnly = true)
	public Optional<Subject> getSubject(Long id) {
		return subjectRepository.findById(id);
	}


	@Transactional(readOnly = true)
	public Collection<Subject> getAllSubjects() {
		return subjectRepository.findAll();
	}

	@Transactional
	public Subject save(Subject subject) {
		return subjectRepository.save(subject);
	}

	@Transactional
	public void delete(Long id) {
		subjectRepository.deleteById(id);
	}
	
}
