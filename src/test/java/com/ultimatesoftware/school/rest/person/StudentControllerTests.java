package com.ultimatesoftware.school.rest.person;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ultimatesoftware.school.domain.person.Student;
import com.ultimatesoftware.school.domain.person.StudentService;
import com.ultimatesoftware.school.domain.subject.SubjectService;
import com.ultimatesoftware.school.rest.subject.SubjectDtoMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
@EnableWebMvc
@Import({
		StudentDtoMapper.class,
		SubjectDtoMapper.class
})
public class StudentControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	@MockBean
	private SubjectService subjectService;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void getAllShouldReturnEmptyList() throws Exception {
		mockMvc.perform(get("/v1/students"))
				.andExpect(status().isOk())
				.andExpect(content().string("[]"));
	}

	@Test
	public void getOneShouldThrowNotFound() throws Exception {
		mockMvc.perform(get("/v1/students/12345"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void createSavesStudent() throws Exception {
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName("fName");
		studentDto.setLastName("lName");

		when(studentService.save(any())).thenReturn(new Student());

		mockMvc.perform(post("/v1/students")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(studentDto)))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteNonExistentStudent() throws Exception {
		mockMvc.perform(delete("/v1/students/1234"))
				.andExpect(status().isOk());
	}

}
