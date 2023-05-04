/*
 * Name: Bonita Li
 * File: MainController.java
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
 * 		DatabaseAccess.java
 * 		DatabaseConfig.java
 * 		LogAccessDeniedHandler.java
 * 		SecurityConfig.java
 * 		UserDetailsServiceImpl.java
 */

/**
 * The Main Controller class is for handling HTML mapping, creating objects for
 * Events and Transactions, sessions, and various model attributes.
 */

package ca.sheridancollege.libon.a4_libon.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.libon.a4_libon.beans.Event;
import ca.sheridancollege.libon.a4_libon.beans.Transaction;
import ca.sheridancollege.libon.a4_libon.database.DatabaseAccess;

@Controller
public class MainController {

	@Autowired
	private DatabaseAccess da;
	
	/**
	 * 
	 * @param model Basic model for adding attributes
	 * @param session Model for session
	 * @return String of index.html
	 */
	@GetMapping("/")
	public String indexPage(Model model, HttpSession session) {
		
		int year = LocalDate.now().getYear();
		model.addAttribute("year", year);
		model.addAttribute("message", "Welcome");
		model.addAttribute("page", "mainIndex");
		
		if (session.getAttribute("events") == null) {
			session.setAttribute("events", da.getEvents());
		}

		model.addAttribute("events", new Event());
		
		return "index.html";
	}

	/**
	 * 
	 * @param model Basic model for adding attributes
	 * @param session Model for sessions
	 * @return String of index.html
	 */
	@GetMapping("/events")
	public String eventsPage(Model model, HttpSession session) {
		
		int year = LocalDate.now().getYear();
		model.addAttribute("year", year);
		model.addAttribute("message", "Welcome");
		model.addAttribute("page", "mainIndex");
		
		if (session.getAttribute("events") == null) {
			session.setAttribute("events", da.getEvents());
		}

		model.addAttribute("events", new Event());
		
		return "index.html";
	}
	
	/**
	 * 
	 * @param model Basic model for adding attributes
	 * @param transaction Transaction model
	 * @return String of /txns/add.html
	 */
	@GetMapping("/add")
	public String transactionPage(Model model, @ModelAttribute Transaction transaction) {
		
		int year = LocalDate.now().getYear();
		model.addAttribute("year", year);
		model.addAttribute("message", "Add Transaction");
		model.addAttribute("page", "addTransc");
		
		model.addAttribute("transaction", new Transaction());
		
		List<Event> events = da.getEvents();
		model.addAttribute("events", events);
		
		return "/txns/add.html";
	}
	
	/**
	 * 
	 * @param model Basic model for adding attributes
	 * @param transaction Model for Transaction
	 * @return String of redirect:/list
	 */
	@PostMapping("/addnew")
	public String addNewTransc(Model model, @ModelAttribute Transaction transaction) {
		
		int year = LocalDate.now().getYear();
		model.addAttribute("year", year);
		model.addAttribute("message", "Add Transaction");
		model.addAttribute("page", "addTransc");
		
		da.addTransaction(transaction);

		return "redirect:/list";
	} 
	
	/**
	 * 
	 * @param model Basic model for adding attributes
	 * @return String of /txns/index.html
	 */
	@GetMapping("/list")
	public String transactionsList(Model model) {
		
		int year = LocalDate.now().getYear();
		model.addAttribute("year", year);
		model.addAttribute("message", "List Transactions");
		model.addAttribute("page", "transcList");
		
		model.addAttribute("transactions", da.getTransactions());
		model.addAttribute("events", da.getEventMap());
		model.addAttribute("rows", da.getTransactionsCount());
		
		return "/txns/index.html";
	}
	
	/**
	 * 
	 * @param model Basic model for adding attributes
	 * @return String of login.html
	 */
	@GetMapping("/login")
	public String loginForm(Model model) {
		
		int year = LocalDate.now().getYear();
		model.addAttribute("year", year);
		model.addAttribute("message", "Sign In");
		model.addAttribute("page", "loginPage");
		
		return "login.html";
	}
	
	/**
	 * 
	 * @param model Basic model for adding attributes
	 * @return String of /error/accessDenied.html
	 */
	@GetMapping("/denied")
	public String accessDenied(Model model) {
		
		int year = LocalDate.now().getYear();
		model.addAttribute("year", year);
		model.addAttribute("message", "Access Denied");
		model.addAttribute("page", "deniedPage");
		
		return "/error/accessDenied.html";
	}
	
}
