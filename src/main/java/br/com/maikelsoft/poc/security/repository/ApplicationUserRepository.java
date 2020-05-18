package br.com.maikelsoft.poc.security.repository;

import java.util.Optional;

import br.com.maikelsoft.poc.security.auth.ApplicationUser;

public interface ApplicationUserRepository {

	Optional<ApplicationUser> findByUsername(String username);
}
