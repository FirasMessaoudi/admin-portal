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
public enum ELanguageCode {
    AR(1),
    EN(2),
    FR(3),
    UR(4),
    FA(4),
    TR(4);

    private final long id;

    ELanguageCode(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public static ELanguageCode fromId(long id) {
        List<ELanguageCode> result = Arrays.stream(ELanguageCode.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (ELanguageCode) CollectionUtils.get(result, 0);
    }
}
