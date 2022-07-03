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
public enum ERitualType {
    EXTERNAL_HAJJ(1),
    INTERNAL_HAJJ(2),
    COURTESY_HAJJ(3),
    EXTERNAL_UMRAH(4),
    INTERNAL_UMRAH(5);
    private final int id;

    ERitualType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ERitualType fromId(int id) {
        List<ERitualType> result = Arrays.stream(ERitualType.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (ERitualType) CollectionUtils.get(result, 0);
    }
}
