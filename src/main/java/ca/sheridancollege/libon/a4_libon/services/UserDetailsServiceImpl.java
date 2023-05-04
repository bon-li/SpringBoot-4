/*
 * Name: Bonita Li
 * File: UserDetailsServiceImpl.java
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
 * 		SecurityConfig.java
 */

/**
 * This class is the UserDetailsServiceImp which handles and authenticates the 
 * user account when the user logs in and gets the user role.
 */

package ca.sheridancollege.libon.a4_libon.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.libon.a4_libon.database.DatabaseAccess;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired @Lazy
	private DatabaseAccess da;
	
	/**
	 * @param username String used to verify username
	 * @return userDetails Object
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		ca.sheridancollege.libon.a4_libon.beans.User user = da.findUserAccount(username);
		if (user==null) {
			System.out.printf("User not found: %s%n", username);
			throw new UsernameNotFoundException("User " + username + " not found in database");
		}
		//get the list of roles from the database
		List<String> roles = da.getRolesById(user.getUserId());
		
		//convert the list of role strings into a list GrantedAuthory objects
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roles != null) {
			for (String role : roles) {
				grantList.add(new SimpleGrantedAuthority(role));
			}
		}
		UserDetails userDetails = (UserDetails)(new User(user.getUsername(), user.getEncryptedPassword(), grantList));
		return userDetails;
	}
}
