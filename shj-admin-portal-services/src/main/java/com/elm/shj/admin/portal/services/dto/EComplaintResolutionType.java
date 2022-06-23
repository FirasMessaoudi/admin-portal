/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Enum for applicant complaint resolution type
 *
 * @author othman alamoud
 * @since 1.2.6
 */

public enum EComplaintResolutionType {

    RESOLVED(2), CLOSED(3);

//    String string;
    private final int crmCode;

    EComplaintResolutionType(int code) {
        this.crmCode = code;
    }

    @JsonCreator
    public static EComplaintResolutionType getCode(int code) {
        for (final EComplaintResolutionType s: EComplaintResolutionType.values()) {
            if (s.crmCode == code) {
                return s;
            }
        }
        return null;
    }

    public int getCrmCode() {
        return crmCode;
    }
}
