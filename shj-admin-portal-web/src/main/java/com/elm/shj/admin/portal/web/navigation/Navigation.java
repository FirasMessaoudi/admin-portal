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
	// Data Segments
	public static final String API_DATA_SEGMENT = "/api/data/segment";
	// Data Requests
	public static final String API_DATA_REQUEST = "/api/data/request";
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
	// Staff Printing Requests
	public static final String API_STAFF_PRINTING_REQUESTS = "/api/staff/print/requests";
	// Dashboard
	public static final String API_DASHBOARD = "/api/dashboard";
	// Error
	public static final String ERROR = "/error";
	// Password Change
	public static final String API_USERS_CHANGE_PWRD = API_USERS + "/change-password";
	// Password Reset
	public static final String API_USERS_RESET_PWRD = API_USERS + "/reset-password";
	// Lookup
	public static final String API_LOOKUP = "/api/lookup";
	// Registration
	public static final String API_REGISTRATION = "/api/register";
	// Integration
	public static final String API_INTEGRATION = "/api/ws";
	// Notification Integration
	public static final String API_NOTIFICATION_INTEGRATION = "/api/ws/notification";
	// notification template management
	public static final String API_NOTIFICATION_TEMPLATE = "/api/notification/template";
	// Incidents
	public static final String API_INCIDENTS = "/api/incidents";
	// Incident Integration
	public static final String API_INCIDENTS_INTEGRATION = "/api/ws/incidents";
	// Chat Contact Integration
	public static final String API_CHAT_CONTACT_INTEGRATION = "/api/ws/chat-contact";
	public static final String API_STAFF_INTEGRATION = "/api/ws/staff";
	// Applicant Cards
	public static final String API_STAFF_CARDS = "/api/staff-cards";
	//huic integration
	public static final String API_HUIC_INTEGRATION = "/api/huic";
	//Data segment
	public static final String API_SURVEY_INTEGRATION = "/api/ws/survey";
	// Applicant and Staff Badge
	public static final String API_BADGE = "/api/ws/badge";
	public static final String API_PRINTING_MANAGEMENT_INTEGRATION = "/api/ws/printing/management";
	// Batch main collection
	public static final String API_BATCH = "/api/ws/batch";

	public static final String API_SUPPLICATIONS ="/api/ws/supplications";
	//Rosary
	public static final String API_ROSARY_INTEGRATION  = "/api/ws/rosary";

}
