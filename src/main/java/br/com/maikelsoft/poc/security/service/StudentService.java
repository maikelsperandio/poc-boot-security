package br.com.maikelsoft.poc.security.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import br.com.maikelsoft.poc.security.model.Student;
import lombok.Getter;

@Service
public class StudentService {

	private @Getter List<Student> students;

	@PostConstruct
	public void config(){
		this.students = Arrays.asList(
					new Student(1, "Maikel Sperandio"), 
					new Student(2, "Joaquim Barbosa"),
					new Student(3, "Tertuliano de Souza")
				);
	}

	public void delete(Integer id) {
//		Student toDelete = null;
//		for(Student std : this.students) {
//			if(std.getId().equals(id)) {
//				toDelete = std;
//				break;
//			}
//		}
//		if(toDelete != null) {
//			this.students.remove(toDelete);
//		}
		System.out.println("Deleted student");
	}

	public void update(Student std) {
		System.out.println("Updating student...".concat(std.toString()));
	}
}
