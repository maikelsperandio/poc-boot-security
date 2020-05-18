package br.com.maikelsoft.poc.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.maikelsoft.poc.security.repository.ApplicationUserRepository;

@Service
public class ApplicationUserService implements UserDetailsService{

	private @Autowired ApplicationUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository
				.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
	}

}
