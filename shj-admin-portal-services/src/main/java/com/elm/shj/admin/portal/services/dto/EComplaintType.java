/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Enum for Complaint Status
 *
 * @author othman alamoud
 * @since 1.2.6
 */
public enum EComplaintType {
    UNDER_PROCESSING(1), RESOLVED(2),CLOSED(3);

    private final int crmCode;

    EComplaintType(int code) {
        this.crmCode = code;
    }

    @JsonCreator
    public static EComplaintType getCode(int code) {
        for (final EComplaintType s: EComplaintType.values()) {
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
