/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enum for marital status
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public enum EMaritalStatus {
    SINGLE(1),
    MARRIED(2),
    WIDOWED(3),
    DIVORCED(4);

    private final int id;

    EMaritalStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static EMaritalStatus fromId(int id) {
        List<EMaritalStatus> result = Arrays.stream(EMaritalStatus.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (EMaritalStatus) CollectionUtils.get(result, 0);
    }
}
