/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enum for arrival city
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public enum EArrivalCity {
    MAKKAH(1),
    MADINA(2);

    private final long id;

    EArrivalCity(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public static EArrivalCity fromId(long id) {
        List<EArrivalCity> result = Arrays.stream(EArrivalCity.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (EArrivalCity) CollectionUtils.get(result, 0);
    }
}
