package com.ultimatesoftware.school.rest.subject;

import org.springframework.stereotype.Component;
import com.ultimatesoftware.school.domain.subject.Subject;

@Component
public class SubjectDtoMapper {

	public SubjectDto mapToDto(Subject domain) {
		SubjectDto dto = new SubjectDto();
		dto.setId(domain.getId());
		dto.setName(domain.getName());
		return dto;
	}

	public void mapToDomain(SubjectDto dto, Subject domain) {
		 domain.setName(dto.getName());
	}
}
