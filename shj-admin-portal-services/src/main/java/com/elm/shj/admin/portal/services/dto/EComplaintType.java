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
    FOOD(1), TRANSPORTATION(2),HOUSING(3),GENERAL(4);

    private final Integer crmCode;

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

    public Integer getCrmCode() {
        return crmCode;
    }
}
