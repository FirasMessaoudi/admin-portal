/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

/**
 * Enum for print request statuses
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public enum EGender {
    MALE("M"),
    FEMALE("F");

    private String code;

    private EGender(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
