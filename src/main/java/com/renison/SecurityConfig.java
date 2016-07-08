package com.renison;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This application is secured at both the URL level for some parts, and the
 * method level for other parts. The URL security is shown inside this code,
 * while method-level annotations are enabled at by
 * {@link EnableGlobalMethodSecurity}.
 *
 * @author Greg Turnquist
 * @author Oliver Gierke
 */
// this needs to be annotated, otherwise the application generates a password
// to protect itself which we don't want, see:
// http://www.sothawo.com/2015/07/build-a-spring-boot-rest-service-with-basic-authentication-for-several-users/
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * This section defines the user accounts which can be used for
	 * authentication as well as the roles each user has.
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().//
				withUser("root").password("root@ept").roles("USER").and().//
				withUser("ron").password("thechampion").roles("USER", "ADMIN");
	}

	/**
	 * This section defines the security policy for the app.
	 * <p>
	 * <ul>
	 * <li>BASIC authentication is supported (enough for this REST-based demo).
	 * </li>
	 * <li>/employees is secured using URL security shown below.</li>
	 * <li>CSRF headers are disabled since we are only testing the REST
	 * interface, not a web one.</li>
	 * </ul>
	 * NOTE: GET is not shown which defaults to permitted.
	 *
	 * @param http
	 * @throws Exception
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().authorizeRequests().//
				antMatchers(HttpMethod.POST, "/tests/**").hasRole("ADMIN").//
				antMatchers(HttpMethod.GET, "/tests/**").hasRole("ADMIN").//
				antMatchers(HttpMethod.DELETE, "/tests/**").hasRole("ADMIN").//
				antMatchers(HttpMethod.PUT, "/tests/**").hasRole("ADMIN").//
				antMatchers(HttpMethod.POST, "/categories/**").hasRole("ADMIN").//
				antMatchers(HttpMethod.GET, "/categories/**").hasRole("ADMIN").//
				antMatchers(HttpMethod.DELETE, "/categories/**").hasRole("ADMIN").//
				antMatchers(HttpMethod.PUT, "/categories/**").hasRole("ADMIN").//
				antMatchers(HttpMethod.GET, "/testComponents/**").hasRole("ADMIN").//
				antMatchers(HttpMethod.PUT, "/testComponents/**").hasRole("ADMIN").//
				antMatchers(HttpMethod.POST, "/testComponents/**").hasRole("ADMIN").//
				antMatchers(HttpMethod.DELETE, "/testComponents/**").hasRole("ADMIN").and().csrf().disable();
	}
}
