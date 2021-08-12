package com.elm.shj.admin.portal.web.error;

import java.util.Map;

/**
 * Custom exception for not found applicants.
 *
 * @author Ahmed Ali (Elnazer)
 * @since 1.1.0
 */

public class CardDetailsNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 1L;

    Map<String, String> errors;

    public CardDetailsNotFoundException() {
        // empty
    }

    /**
     * Constructor with message
     *
     * @param message
     */
    public CardDetailsNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor with message and errors
     *
     * @param message
     * @param errors
     */
    public CardDetailsNotFoundException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    /**
     * Constructor with message and details
     *
     * @param message the exception message
     * @param th      the thrown exception
     */
    public CardDetailsNotFoundException(String message, Throwable th) {
        super(message, th);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}

