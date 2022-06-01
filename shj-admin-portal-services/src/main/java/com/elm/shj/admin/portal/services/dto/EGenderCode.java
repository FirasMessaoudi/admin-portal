/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enum for print request statuses
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public enum EGenderCode {
    M(1),
    F(2);

    private final long id;

    EGenderCode(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public static EGenderCode fromId(long id) {
        List<EGenderCode> result = Arrays.stream(EGenderCode.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (EGenderCode) CollectionUtils.get(result, 0);
    }
}
