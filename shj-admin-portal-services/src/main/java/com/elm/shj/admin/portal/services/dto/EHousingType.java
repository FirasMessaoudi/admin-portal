/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enum for ritual types
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public enum EHousingType {
    CAMP(1),
    HOTEL(2),
    BUILDING(3),
    APARTMENT(4);
    private final int id;

    EHousingType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static EHousingType fromId(int id) {
        List<EHousingType> result = Arrays.stream(EHousingType.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (EHousingType) CollectionUtils.get(result, 0);
    }
}
