/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.error;

import java.util.Map;

/**
 * Custom exception for not allowed Action.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public class ActionNotAllowedException extends RuntimeException {


    Map<String, String> errors;

    public ActionNotAllowedException() {
        // empty
    }

    /**
     * Constructor with message
     *
     * @param message
     */
    public ActionNotAllowedException(String message) {
        super(message);
    }

    /**
     * Constructor with message and errors
     *
     * @param message
     * @param errors
     */
    public ActionNotAllowedException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    /**
     * Constructor with message and details
     *
     * @param message the exception message
     * @param th      the thrown exception
     */
    public ActionNotAllowedException(String message, Throwable th) {
        super(message, th);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}

