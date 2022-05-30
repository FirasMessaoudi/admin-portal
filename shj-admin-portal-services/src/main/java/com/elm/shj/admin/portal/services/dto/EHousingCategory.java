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
public enum EHousingCategory {
    A(1),
    B(2),
    C(3),
    D(4);


    private final long id;

    EHousingCategory(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public static EHousingCategory fromId(long id) {
        List<EHousingCategory> result = Arrays.stream(EHousingCategory.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return (EHousingCategory) CollectionUtils.get(result, 0);
    }
}
