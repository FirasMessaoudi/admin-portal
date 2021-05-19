/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

/**
 * Enum for data segments
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public enum EDataSegment {

    APPLICANT_DATA(1),
    APPLICANT_RELATIVES_DATA(2),
    APPLICANT_HEALTH_DATA(3),
    APPLICANT_IMMUNIZATION_DATA(4),
    APPLICANT_DISEASE_DATA(5),
    APPLICANT_SPECIAL_NEEDS_DATA(6);

    private final long id;

    EDataSegment(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }
}
