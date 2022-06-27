/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Enum for Incident Status
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public enum EIncidentStatus {
    UNDER_PROCESSING(1), RESOLVED(2),CLOSED(3);

    private final Integer crmCode;

    EIncidentStatus(int code) {
        this.crmCode = code;
    }

    @JsonCreator
    public static EIncidentStatus getCode(int code) {
        for (final EIncidentStatus s: EIncidentStatus.values()) {
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
