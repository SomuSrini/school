package com.ultimatesoftware.school.rest.person;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.ultimatesoftware.school.domain.person.Student;
import com.ultimatesoftware.school.domain.person.StudentService;
import com.ultimatesoftware.school.domain.subject.Subject;
import com.ultimatesoftware.school.domain.subject.SubjectService;

@RestController
@RequestMapping("/v1/students")
public class StudentController {

	private final StudentService studentService;
	private final SubjectService subjectService;
	private final StudentDtoMapper studentDtoMapper;

	@Autowired
	public StudentController(StudentService studentService, SubjectService subjectService,
			StudentDtoMapper studentDtoMapper) {
		this.studentService = studentService;
		this.subjectService = subjectService;
		this.studentDtoMapper = studentDtoMapper;
	}

	@GetMapping
	public Collection<StudentDto> getAll() {
		return studentService.getAllStudents().stream()
				.map(studentDtoMapper::mapToDto)
				.collect(Collectors.toSet());
	}

	@GetMapping("/{id}")
	public StudentDto getOne(@PathVariable Long id) {
		Optional<Student> student = studentService.getStudent(id);
		if (!student.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
		}
		return studentDtoMapper.mapToDto(student.get());
	}

	@PostMapping
	public StudentDto create(@RequestBody StudentDto studentDto) {
		Student student = new Student();
		studentDtoMapper.mapToDomain(studentDto, student);
		studentService.save(student);
		return studentDtoMapper.mapToDto(student);
	}

	@PutMapping("/{id}/subjects")
	public StudentDto addClasses(@PathVariable Long id, @RequestBody Set<Long> subjectIds) {
		Optional<Student> student = studentService.getStudent(id);
		if (!student.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
		}

		Set<Subject> subjects = new HashSet<>(subjectIds.size());
		for (Long subjectId : subjectIds) {
			Optional<Subject> subject = subjectService.getSubject(subjectId);
			if (!subject.isPresent()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
			}
			subjects.add(subject.get());
		}

		student.get().setSubjects(subjects);
		studentService.save(student.get());
		return studentDtoMapper.mapToDto(student.get());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		studentService.delete(id);
	}
}
