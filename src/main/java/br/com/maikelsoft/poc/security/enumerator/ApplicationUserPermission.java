package br.com.maikelsoft.poc.security.enumerator;

import lombok.Getter;

public enum ApplicationUserPermission {

	STUDENT_READ("student:read"),
	STUDENT_WRITE("student:write"),
	COURSE_READ("course:read"),
	COURSE_WRITE("course:write");

	private @Getter String permission;

	private ApplicationUserPermission(String perm) {
		this.permission = perm;
	}
}
