package br.com.maikelsoft.poc.security.enumerator;

import static br.com.maikelsoft.poc.security.enumerator.ApplicationUserPermission.COURSE_READ;
import static br.com.maikelsoft.poc.security.enumerator.ApplicationUserPermission.COURSE_WRITE;
import static br.com.maikelsoft.poc.security.enumerator.ApplicationUserPermission.STUDENT_READ;
import static br.com.maikelsoft.poc.security.enumerator.ApplicationUserPermission.STUDENT_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

import lombok.Getter;

public enum ApplicationUserRole {
	STUDENT(Sets.newHashSet()),
	ADMINTRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ)),
	ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));

	private @Getter Set<ApplicationUserPermission> permissions;

	private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		Set<SimpleGrantedAuthority> collect = this.permissions.stream()
													.map(perm -> new SimpleGrantedAuthority(perm.getPermission()))
													.collect(Collectors.toSet());
		collect.add(new SimpleGrantedAuthority("ROLE_".concat(this.name())));
		return collect;
	}
}
