/*
 * Name: Bonita Li
 * File: User.java
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
 * 		SecurityWebApplicationInitializer.java
 * 		MainController.java
 * 		DatabaseAccess.java
 * 		DatabaseConfig.java
 * 		LogAccessDeniedHandler.java
 * 		SecurityConfig.java
 * 		UserDetailsServiceImpl.java
 */

/**
 * The User class creates a User object. 
 */

package ca.sheridancollege.libon.a4_libon.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//variables for creating User object
	private long userId;
	@NonNull
	private String username;
	@NonNull
	private String encryptedPassword;
	private boolean enabled;
}
