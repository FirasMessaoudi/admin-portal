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
public enum ECustomerTypeCRM {
    PILGRIM(1), STAFF(2);

    private final Integer crmCode;

    ECustomerTypeCRM(int code) {
        this.crmCode = code;
    }

    @JsonCreator
    public static ECustomerTypeCRM getCode(int code) {
        for (final ECustomerTypeCRM s: ECustomerTypeCRM.values()) {
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
