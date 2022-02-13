/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

/**
 * Enum for User Type
 *
 * @author salzoubi
 * @since 1.1.0
 */
public enum EUserType {

    STAFF(1), APPLICANT(2);

    private final int id;

    EUserType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
