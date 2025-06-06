/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

/**
 * Class for authorities constants.
 *
 * @author ahmad flaifel
 * @since 1.8.0
 */
public final class AuthorityConstants {


    public static final String USER_MANAGEMENT = "USER_MANAGEMENT";
    public static final String ADD_USER = "ADD_USER";
    public static final String EDIT_USER = "EDIT_USER";
    public static final String CHANGE_USER_STATUS = "CHANGE_USER_STATUS";
    public static final String RESET_PASSWORD = "RESET_PASSWORD";
    public static final String DELETE_USER = "DELETE_USER";
    public static final String ROLE_MANAGEMENT = "ROLE_MANAGEMENT";
    public static final String ADD_ROLE = "ADD_ROLE";
    public static final String EDIT_ROLE = "EDIT_ROLE";
    public static final String DELETE_ROLE = "DELETE_ROLE";
    public static final String CHANGE_ROLE_STATUS = "CHANGE_ROLE_STATUS";
    public static final String RESET_USER_PASSWORD = "RESET_USER_PASSWORD";
    public static final String UPDATE_MY_PROFILE = "UPDATE_MY_PROFILE";
    public static final String VIEW_MY_PROFILE = "VIEW_MY_PROFILE";


    //Printing Request Management
    public static final String APPLICANT_PRINTING_REQUEST_MANAGEMENT = "APPLICANT_PRINTING_REQUEST_MANAGEMENT";
    public static final String STAFF_PRINTING_REQUEST_MANAGEMENT = "STAFF_PRINTING_REQUEST_MANAGEMENT";
    public static final String VIEW_PRINTING_REQUEST_DETAILS = "VIEW_PRINTING_REQUEST_DETAILS";
    public static final String ADD_PRINTING_REQUEST = "ADD_PRINTING_REQUEST";
//    public static final String VIEW_STAFF_PRINTING_REQUEST_DETAILS = "VIEW_STAFF_PRINTING_REQUEST_DETAILS";
//    public static final String ADD_STAFF_PRINTING_REQUEST = "ADD_STAFF_PRINTING_REQUEST";

    //Card Management
    public static final String CARD_MANAGEMENT = "CARD_MANAGEMENT";
    public static final String VIEW_CARD_DETAILS = "VIEW_CARD_DETAILS";
    public static final String UPDATE_CARD = "UPDATE_CARD";
    public static final String ACTIVATE_CARD = "ACTIVATE_CARD";
    public static final String CANCEL_CARD = "CANCEL_CARD";
    public static final String SUSPEND_CARD = "SUSPEND_CARD";
    public static final String REISSUE_CARD = "REISSUE_CARD";
    public static final String ADD_CARD = "ADD_CARD";

    //Data Request Management
    public static final String DATA_REQUEST_MANAGEMENT = "DATA_REQUEST_MANAGEMENT";
    public static final String VIEW_DATA_REQUEST_DETAILS = "VIEW_DATA_REQUEST_DETAILS";
    public static final String CREATE_DATA_REQUEST = "CREATE_DATA_REQUEST";

    //Integration WebService Call
    public static final String INTEGRATION_WEB_SERVICE_CALL = "INTEGRATION_WEB_SERVICE_CALL";
    //notification management
    public static final String NOTIFICATION_MANAGEMENT = "NOTIFICATION_MANAGEMENT";
    public static final String SYSTEM_DEFINED_NOTIFICATION_DETAILS = "SYSTEM_DEFINED_NOTIFICATION_DETAILS";
    public static final String USER_DEFINED_NOTIFICATION_MANAGEMENT = "USER_DEFINED_NOTIFICATION_MANAGEMENT";
    //Dashboard
    public static final String ADMIN_DASHBOARD = "ADMIN_DASHBOARD";
    public static final String MOBILE_TRACKING_DASHBOARD = "MOBILE_TRACKING_DASHBOARD";
    public static final String STATISTICAL_DASHBOARD = "STATISTICAL_DASHBOARD";
    public static final String INCIDENT_DASHBOARD = "INCIDENT_DASHBOARD";

    //HUIC integration API's
    public static final String HUIC_INTEGRATION_WEB_SERVICE_CALL = "HUIC_INTEGRATION_WEB_SERVICE_CALL";

    private AuthorityConstants() {
        // creating instances is not allowed
    }
}
