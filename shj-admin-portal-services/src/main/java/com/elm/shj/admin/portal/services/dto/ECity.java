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
public enum ECity {
    MAKKAH(1), HOLY_SITES(2),MADINAH(3),JEDDAH(4),OTHERS(5);

    private final int crmCode;

    ECity(int code) {
        this.crmCode = code;
    }

    @JsonCreator
    public static ECity getCode(int code) {
        for (final ECity s: ECity.values()) {
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
