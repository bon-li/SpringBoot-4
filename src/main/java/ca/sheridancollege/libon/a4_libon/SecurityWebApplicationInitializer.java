/*
 * Name: Bonita Li
 * File: SecurityWebApplicationInitializer.java
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
 * 		MainController.java
 * 		DatabaseAccess.java
 * 		DatabaseConfig.java
 * 		LogAccessDeniedHandler.java
 * 		SecurityConfig.java
 * 		UserDetailsServiceImpl.java
 */

/**
 * This class is the SecurityWebApplicationInitializer that ensures that all
 * requests are filtered to be authenticated. 
 */

package ca.sheridancollege.libon.a4_libon;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import ca.sheridancollege.libon.a4_libon.security.SecurityConfig;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	/**
	 * Super class constructor
	 */
	public SecurityWebApplicationInitializer() {
		super(SecurityConfig.class);
	}

}