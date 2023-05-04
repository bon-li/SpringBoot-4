/*
 * Name: Bonita Li
 * File: SecurityConfig.java
 * Other Files in this Project:
 * 		index.html
 * 		index.html
 * 		add.html
 * 		login.html
 * 		main.html
 * 		accessDenied.html
 * 		schema.sql
 * 		data.sql
 * 		stylesheet.css
 * 		Event.java
 * 		Transaction.java
 * 		User.java
 * 		SecurityWebApplicationInitializer.java
 * 		MainController.java
 * 		DatabaseAccess.java
 * 		DatabaseConfig.java
 * 		LogAccessDeniedHandler.java
 * 		UserDetailsServiceImpl.java
 */

/**
 * The SecurityConfig class handles authentication and requests from users
 * logging in and requests to view web pages.
 */

package ca.sheridancollege.libon.a4_libon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ca.sheridancollege.libon.a4_libon.security.LogAccessDeniedHandler;
import ca.sheridancollege.libon.a4_libon.services.UserDetailsServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LogAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	/**
	 * @param http Handles http security
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//permits MANAGER role to view content in /txns folder
		http.authorizeRequests()
			.antMatchers("/add", "/addnew", "/list").hasRole("MANAGER")
		//permits everyone to view index, scripts, css, and images
			.antMatchers("/", "/events", "/scripts/**", "/css/**", "/images/**").permitAll()
			.anyRequest().authenticated()
		.and()
		//sets loginPage to /login 
			.formLogin().loginPage("/login")
				.defaultSuccessUrl("/list", true).permitAll()
		.and()
		//maps logout
			.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout").permitAll()
		.and()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
	
	/**
	 * 
	 * @return BCryptPasswordEncoder method
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * @param auth Authenticates user details
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
