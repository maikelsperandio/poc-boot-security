package br.com.maikelsoft.poc.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maikelsoft.poc.security.model.Student;
import br.com.maikelsoft.poc.security.service.StudentService;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

	private @Autowired StudentService studentService;

	@GetMapping(path= "/{id}")
	public Student getStudent(@PathVariable("id") Integer id) {
		return studentService.getStudents()
					.stream()
					.filter(student -> id.equals(student.getId()))
					.findFirst()
					.orElseThrow(() -> new IllegalStateException("Student " + id + " does not exists"));
	}

}
