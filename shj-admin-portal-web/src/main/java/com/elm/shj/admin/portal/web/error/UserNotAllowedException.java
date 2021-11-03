/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.error;

import java.util.Map;

/**
 * Custom exception for not allowed user.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public class UserNotAllowedException extends RuntimeException {

    private static final long serialVersionUID = -2055467894820483788L;

    Map<String, String> errors;

    public UserNotAllowedException() {
        // empty
    }

    /**
     * Constructor with message
     *
     * @param message
     */
    public UserNotAllowedException(String message) {
        super(message);
    }

    /**
     * Constructor with message and errors
     *
     * @param message
     * @param errors
     */
    public UserNotAllowedException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    /**
     * Constructor with message and details
     *
     * @param message the exception message
     * @param th      the thrown exception
     */
    public UserNotAllowedException(String message, Throwable th) {
        super(message, th);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}

