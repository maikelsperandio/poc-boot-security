package br.com.maikelsoft.poc.security.config;

import static br.com.maikelsoft.poc.security.enumerator.ApplicationUserRole.STUDENT;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.maikelsoft.poc.security.auth.ApplicationUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	private @Autowired PasswordEncoder passEncoder;

	private @Autowired ApplicationUserService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
			.antMatchers("/api/**").hasRole(STUDENT.name())
//			.antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//			.antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//			.antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//			.antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
			.anyRequest()
			.authenticated()
			.and()
//			.httpBasic();
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/courses", true)
				.passwordParameter("password") // In case in the html has not the default values
				.usernameParameter("username") // In case in the html has not the default values
			.and()
			.rememberMe() // default to 2 weeks
				.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(20)) // Overriding the default 2 weeks time
				.key("somethingverysecured") // Overriding the key that Security will use to generate the hash of username and expiration time
				.rememberMeParameter("remember-me") // In case in the html has not the default values
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))// If the csrf is enabled then the POST method is necessary instead of GET
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID", "remember-me")
				.logoutSuccessUrl("/login");
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passEncoder);
		provider.setUserDetailsService(userService);

		return provider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	// Removed when implemented UserDetailsService 
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		UserDetails benne = User.builder()
//								.username("benne")
//								.password(passEncoder.encode("123"))
////								.roles(STUDENT.name())
//								.authorities(STUDENT.getGrantedAuthorities())
//								.build();
//
//		UserDetails joaquim = User.builder()
//								.username("joaquim")
//								.password(passEncoder.encode("pass123"))
////								.roles(ADMIN.name())
//								.authorities(ADMIN.getGrantedAuthorities())
//								.build();
//
//		UserDetails tom = User.builder()
//				.username("tom")
//				.password(passEncoder.encode("tom123"))
////				.roles(ADMINTRAINEE.name())
//				.authorities(ADMINTRAINEE.getGrantedAuthorities())
//				.build();
//
//		return new InMemoryUserDetailsManager(benne, joaquim, tom);
//	}

}
