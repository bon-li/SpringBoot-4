/*
 * Name: Bonita Li
 * File: LogAccessDeniedHandler.java
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
 * 		SecurityConfig.java
 * 		UserDetailsServiceImpl.java
 */

/**
 * The LogAccessDeniedHandler class has a method that handles users that are 
 * denied access to a web page and redirected to /denied
 */

package ca.sheridancollege.libon.a4_libon.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class LogAccessDeniedHandler implements AccessDeniedHandler {

	/**
	 * @param request Retrieves data from methods
	 * @param response Responses with redirect
	 * @param accessDeniedException Implements AccessDeniedHandler
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		response.sendRedirect(request.getContextPath() + "/denied");
	}

}