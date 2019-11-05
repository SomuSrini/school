package com.ultimatesoftware.school.rest.person;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ultimatesoftware.school.domain.person.Student;
import com.ultimatesoftware.school.rest.subject.SubjectDtoMapper;

@Component
public class StudentDtoMapper {

	private final SubjectDtoMapper subjectDtoMapper;

	@Autowired
	public StudentDtoMapper(SubjectDtoMapper subjectDtoMapper) {
		this.subjectDtoMapper = subjectDtoMapper;
	}

	public StudentDto mapToDto(Student domain) {
		StudentDto dto = new StudentDto();
		dto.setId(domain.getId());
		dto.setLastName(domain.getLastName());
		dto.setFirstName(domain.getFirstName());
		dto.setSubjects(domain.getSubjects().stream()
				.map(subjectDtoMapper::mapToDto)
				.collect(Collectors.toSet()));
		return dto;
	}

	public void mapToDomain(StudentDto dto, Student domain) {
		domain.setFirstName(dto.getLastName());
		domain.setFirstName(dto.getFirstName());
	}

}
