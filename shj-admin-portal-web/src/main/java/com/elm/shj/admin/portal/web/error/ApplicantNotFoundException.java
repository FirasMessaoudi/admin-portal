/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for not found applicants.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Applicant Not Found")
public class ApplicantNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2055467894820483788L;

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
     * Constructor with message and details
     * @param message the exception message
     * @param th the thrown exception
     */
    public ApplicantNotFoundException(String message, Throwable th) {
        super(message, th);
    }
}

