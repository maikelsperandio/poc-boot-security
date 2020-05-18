package br.com.maikelsoft.poc.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maikelsoft.poc.security.model.Student;
import br.com.maikelsoft.poc.security.service.StudentService;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {

	private @Autowired StudentService studentService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
	public List<Student> getAll(){
		return studentService.getStudents();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('student:write')")
	public void registerNewStudent(@RequestBody Student std) {
		System.out.println("Student added!");
	}

	@DeleteMapping(path="/{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public void delete(@PathVariable("id") Integer id) {
		studentService.delete(id);
	}

	@PutMapping
	@PreAuthorize("hasAuthority('student:write')")
	public void update(@RequestBody Student std) {
		studentService.update(std);
	}
}
