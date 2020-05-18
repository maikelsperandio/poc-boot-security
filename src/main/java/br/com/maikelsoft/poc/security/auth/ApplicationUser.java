package br.com.maikelsoft.poc.security.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

public class ApplicationUser implements UserDetails{

	private static final long serialVersionUID = 1L;

	private @Getter Collection<? extends GrantedAuthority> authorities;
	private @Getter String username;
	private @Getter String password;

	public ApplicationUser(Collection<? extends GrantedAuthority> grantedAuthorities, String username, String password) {
		super();
		this.authorities = grantedAuthorities;
		this.username = username;
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
