package br.com.maikelsoft.poc.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import br.com.maikelsoft.poc.security.auth.ApplicationUser;
import br.com.maikelsoft.poc.security.enumerator.ApplicationUserRole;
import br.com.maikelsoft.poc.security.repository.ApplicationUserRepository;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserRepository{

	private @Autowired PasswordEncoder passEncoder;

	@Override
	public Optional<ApplicationUser> findByUsername(String username) {
		return getApplicationUsers()
				.stream()
				.filter(appUser -> username.equals(appUser.getUsername()))
				.findFirst();
	}

	private List<ApplicationUser> getApplicationUsers(){
		List<ApplicationUser> users = Lists.newArrayList(
				new ApplicationUser(ApplicationUserRole.STUDENT.getGrantedAuthorities(), "benne", passEncoder.encode("123")),
				new ApplicationUser(ApplicationUserRole.ADMIN.getGrantedAuthorities(), "joaquim", passEncoder.encode("123")),
				new ApplicationUser(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities(), "tom", passEncoder.encode("123"))
		);
		return users;
	}
}
