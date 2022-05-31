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
public enum ERPackageType {
    ECONOMIC(1),
    NORMAL(2),
    VIP(3);
    private final long id;

    ERPackageType(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public static ERPackageType fromId(long id) {
        List<ERPackageType> result = Arrays.stream(ERPackageType.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return (ERPackageType) CollectionUtils.get(result, 0);
    }
}
