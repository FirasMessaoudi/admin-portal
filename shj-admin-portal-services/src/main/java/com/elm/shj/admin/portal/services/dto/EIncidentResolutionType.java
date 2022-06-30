/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Enum for applicant incident resolution type
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */

public enum EIncidentResolutionType {

    MARK_AS_RESOLVED(2), MARK_AS_CLOSED(3);

    private final Integer crmCode;

    EIncidentResolutionType(int code) {
        this.crmCode = code;
    }

    @JsonCreator
    public static EIncidentResolutionType getCode(int code) {
        for (final EIncidentResolutionType s: EIncidentResolutionType.values()) {
            if (s.crmCode == code) {
                return s;
            }
        }
        return null;
    }

    public Integer getCrmCode() {
        return crmCode;
    }
}
