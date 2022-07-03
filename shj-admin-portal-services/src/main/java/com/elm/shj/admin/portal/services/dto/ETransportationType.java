/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enum for transportation type
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public enum ETransportationType {
    TRAIN(1),
    BUS(2),
    CAR(3),
    AIRPLANE(4);

    private final int id;

    ETransportationType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ETransportationType fromId(int id) {
        List<ETransportationType> result = Arrays.stream(ETransportationType.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (ETransportationType) CollectionUtils.get(result, 0);
    }
}
