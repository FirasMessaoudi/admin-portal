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
public enum EComplaintStatus {
    UNDER_PROCESSING(1), RESOLVED(2),CLOSED(3);

    private final int code;

    EComplaintStatus(int code) {
        this.code = code;
    }

    @JsonCreator
    public static EComplaintStatus getCode(int code) {
        for (final EComplaintStatus s: EComplaintStatus.values()) {
            if (s.code== code) {
                return s;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }
}
