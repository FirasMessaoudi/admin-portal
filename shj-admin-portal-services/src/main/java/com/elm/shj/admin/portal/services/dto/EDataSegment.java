/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enum for data segments
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public enum EDataSegment {

    APPLICANT_DATA(1),
    APPLICANT_RITUAL_DATA(2),
    APPLICANT_RELATIVES_DATA(3),
    APPLICANT_HEALTH_DATA(4),
    APPLICANT_IMMUNIZATION_DATA(5),
    APPLICANT_DISEASE_DATA(6),
    APPLICANT_EMERGENCY_DATA(7),
    STAFF_MAIN_DATA(8),
    STAFF_RITUAL_DATA(9),
    STAFF_APPLICANT_GROUP_DATA(10),
    STAFF_FULL_MAIN_DATA(11),
    MAIN_GROUP_DATA(12),
    GROUP_DATA(13);

    private final long id;

    EDataSegment(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public static EDataSegment fromId(long id) {
        List<EDataSegment> result = Arrays.stream(EDataSegment.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return (EDataSegment)CollectionUtils.get(result, 0);
    }
}
