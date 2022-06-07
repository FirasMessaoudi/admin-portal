/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enum for meal type
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public enum EMealType {
    FULL_BOARD(1),
    BREAKFAST_DINNER(2),
    BREAKFAST_LUNCH(3),
    ONLY_BREAKFAST(4);

    private final int id;

    EMealType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static EMealType fromId(int id) {
        List<EMealType> result = Arrays.stream(EMealType.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (EMealType) CollectionUtils.get(result, 0);
    }
}
