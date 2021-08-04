/*
 * Copyright (c) 2017 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.error;

import java.util.Map;

/**
 * Error model for all API calls
 *
 * @author Aymen Dhaoui
 * @since 1.0.0
 */
public class ApiErrorResponse {

    private int status;
    private String message;
    private Map<String, String> errors;

    public ApiErrorResponse(int status, String message, Map<String, String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, String> getErrors() {
        return this.errors;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
}
