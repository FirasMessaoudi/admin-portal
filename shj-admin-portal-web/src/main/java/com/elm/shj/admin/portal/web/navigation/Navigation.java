/*
 * Copyright (c) 2017 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.navigation;

/**
 * This class centralizes navigation urls.
 * 
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class Navigation {

	private Navigation() {
		// private constructor to prevent construction
	}

	// Authentication
	public static final String API_AUTH = "/api/auth";
	// Users
	public static final String API_USERS = "/api/users";
	// Roles
	public static final String API_ROLES = "/api/roles";
	// Rules
	public static final String API_RULES = "/api/rules";
	// Applicants
	public static final String API_APPLICANTS = "/api/applicants";
	// Applicant Cards
	public static final String API_APPLICANT_CARDS = "/api/cards";
	// Cards
	public static final String API_CARDS = "/api/cards";
	// Printing Requests
	public static final String API_PRINTING_REQUESTS = "/api/print/requests";
	// Dashboard
	public static final String API_DASHBOARD = "/api/dashboard";
	// Error
	public static final String ERROR = "/error";
	// Password Change
	public static final String API_USERS_CHANGE_PWRD = API_USERS+"/change-password";
	// Password Reset
	public static final String API_USERS_RESET_PWRD = API_USERS+"/reset-password";
	// Lookup
	public static final String API_LOOKUP = "/api/lookup";
	// Registration
	public static final String API_REGISTRATION = "/api/register";

}
