/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.error;

import java.util.Map;

/**
 * Custom exception for not found applicants.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public class ApplicantNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2055467894820483788L;

    Map<String, String> errors;

    public ApplicantNotFoundException() {
        // empty
    }

    /**
     * Constructor with message
     * @param message
     */
    public ApplicantNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor with message and errors
     * @param message
     * @param errors
     */
    public ApplicantNotFoundException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    /**
     * Constructor with message and details
     * @param message the exception message
     * @param th the thrown exception
     */
    public ApplicantNotFoundException(String message, Throwable th) {
        super(message, th);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}

