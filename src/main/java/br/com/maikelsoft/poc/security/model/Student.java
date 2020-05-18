package br.com.maikelsoft.poc.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Student {

	private @Getter @Setter Integer id;
	private @Getter @Setter String name;
}
