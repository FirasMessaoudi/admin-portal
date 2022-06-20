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
public enum EOrganizerTypes {

    ESTABLISHMENT(1),
    MISSION(2),
    INTERNAL_HAJ_COMPANY(3),
    EXTERNAL_HAJ_COMPANY(4),
    SERVICE_GROUP(5),
    GOVERNMENT_AGENCY(6);


    private final long id;

    EOrganizerTypes(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public static EOrganizerTypes fromId(long id) {
        List<EOrganizerTypes> result = Arrays.stream(EOrganizerTypes.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return (EOrganizerTypes)CollectionUtils.get(result, 0);
    }
}
