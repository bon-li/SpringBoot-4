/*
 * Name: Bonita Li
 * File: DatabaseAccess.java
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
 * 		DatabaseConfig.java
 * 		LogAccessDeniedHandler.java
 * 		SecurityConfig.java
 * 		UserDetailsServiceImpl.java
 */

/**
 * Database Access class is for handling retrieving of data from the database
 * with SQL strings to insert new rows, counting rows, finding username, and
 * retrieving roles.
 */

package ca.sheridancollege.libon.a4_libon.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.libon.a4_libon.beans.Event;
import ca.sheridancollege.libon.a4_libon.beans.Transaction;
import ca.sheridancollege.libon.a4_libon.beans.User;

@Repository
public class DatabaseAccess {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	/**
	 * 
	 * @return Events list
	 */
	public List<Event> getEvents() {
		
		//select all columns from events table in alphabetical order
		String sql = "SELECT * FROM events ORDER BY eventName;";
		
		//creates events list
		List<Event> events = jdbc.query(sql, new BeanPropertyRowMapper<Event>(Event.class));
		return events;
	}
	
	/**
	 * 
	 * @param transaction Transaction model
	 * @return JDBC update with sql and params
	 */
	public int addTransaction(Transaction transaction) {
		
		//add new transaction into transactions table
		String sql = "INSERT INTO transactions (event, numTickets, contact)"
				+ "VALUES ((SELECT id FROM events WHERE id = :event), "
				+ ":numTickets, :contact);";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("event", transaction.getEvent())
			.addValue("numTickets", transaction.getNumTickets())
			.addValue("contact", transaction.getContact());
		return jdbc.update(sql, params);
	}
	
	/**
	 * 
	 * @return Transactions list
	 */
	public List<Transaction> getTransactions() {
		
		//select all transactions from transactions table
		String sql = "SELECT * FROM transactions;";
		
		//transactions list created
		List<Transaction> transactions = jdbc.query(sql, 
				new BeanPropertyRowMapper<Transaction>(Transaction.class));
		return transactions;
	}
	/**
	 * 
	 * @param id Id retrieves event by id
	 * @return Event object
	 */
	public Event getEvent(int id) {
		
		//select all events from events table by id
		String sql = "SELECT * FROM events WHERE id=:id;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		//create events list to hold Event object
		List<Event> events = jdbc.query(sql, params, 
				new BeanPropertyRowMapper<Event>(Event.class));
		if (events.size() > 0) {
			return events.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @return Event Map with Integer as key, String as value
	 */
	public Map<Integer, String> getEventMap() {
		
		//select all events from events table
		String sql = "SELECT * FROM events;";
		
		//creating events HashMap to hold key and eventName
		HashMap<Integer, String> events = new HashMap<Integer, String>();
		List<Map<String, Object>> rows = jdbc.queryForList(sql, new HashMap());
		for(Map<String, Object> row: rows) {
			Event event = new Event();
			event.setId((Integer)(row.get("id")));
			event.setEventName((String)(row.get("eventName")));
			events.put(Integer.valueOf(event.getId()), event.getEventName());
		}
		return events;
	}
	
	/**
	 * 
	 * @return Count of transactions 
	 */
	public Long getTransactionsCount() {
		
		//count all rows from transactions table
		String sql = "SELECT COUNT (*) FROM transactions;";
		return jdbc.queryForObject(sql, new HashMap(), Long.TYPE);
	}
	
	/**
	 * 
	 * @param username Username string used for comparing
	 * @return User object in a list
	 */
	public User findUserAccount(String username) {
		
		//select all rows from users table and compare by username param
		String sql = "SELECT * FROM users WHERE username=:username;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);
		
		//create arraylist to hold user object
		ArrayList<User> users = (ArrayList<User>)jdbc.query(sql,params, 
				new BeanPropertyRowMapper<User>(User.class));
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param userId UserId retrieves roles
	 * @return Roles object
	 */
	public List<String> getRolesById(long userId) {
		
		//select userid and rolename from user user_role and roles table
		String sql = "SELECT user_role.userid, roles.rolename "
				+ "FROM user_role, roles WHERE user_role.roleid = roles.roleid "
				+ "AND user_role.userid=:userid;";
		
		//create arraylist to hold rolenames
		ArrayList<String> roles = new ArrayList<String>();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userid", userId);
		List<Map<String,Object>> rows = jdbc.queryForList(sql, params);
		for (Map<String,Object> row : rows) {
			roles.add((String)row.get("roleName"));
		}
		return roles;
	}
	
}