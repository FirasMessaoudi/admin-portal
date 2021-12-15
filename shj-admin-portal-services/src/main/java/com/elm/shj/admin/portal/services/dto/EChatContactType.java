/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

/**
 * Enum for applicant chat contact types
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public enum EChatContactType {

    STAFF(1), APPLICANT(2);

    private final int id;

    EChatContactType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
