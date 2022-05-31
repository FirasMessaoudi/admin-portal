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
    HOTEL(1),
    CAMP(2),
    BUILDING(3);
    private final long id;

    EHousingType(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public static EHousingType fromId(long id) {
        List<EHousingType> result = Arrays.stream(EHousingType.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (EHousingType) CollectionUtils.get(result, 0);
    }
}
