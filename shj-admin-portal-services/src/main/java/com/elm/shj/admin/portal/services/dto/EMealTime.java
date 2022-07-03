/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enum for meal time
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public enum EMealTime {
    BREAKFAST(1),
    LUNCH(2),
    DINNER(3),
    OTHERS(4);

    private final int id;

    EMealTime(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static EMealTime fromId(int id) {
        List<EMealTime> result = Arrays.stream(EMealTime.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (EMealTime) CollectionUtils.get(result, 0);
    }
}
