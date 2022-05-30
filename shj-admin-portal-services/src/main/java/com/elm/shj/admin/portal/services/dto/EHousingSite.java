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
public enum EHousingSite {
    MENA(1),
    MUZDALIFA(2),
    MAKKAH(3),
    MADINA(4),
    ARAFAT(5);

    private final long id;

    EHousingSite(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public static EHousingSite fromId(long id) {
        List<EHousingSite> result = Arrays.stream(EHousingSite.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return (EHousingSite) CollectionUtils.get(result, 0);
    }
}
