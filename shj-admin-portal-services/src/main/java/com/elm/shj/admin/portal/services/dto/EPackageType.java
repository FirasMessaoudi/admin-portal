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
public enum EPackageType {
    ECONOMIC(1),
    NORMAL(2),
    VIP(3);
    private final int id;

    EPackageType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static EPackageType fromId(int id) {
        List<EPackageType> result = Arrays.stream(EPackageType.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (EPackageType) CollectionUtils.get(result, 0);
    }
}
