/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enum for comapny types
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public enum ECompanyType {
    ESTABLISHMENT(1),
    MISSION(2),
    INTERNAL_HAJJ_COMPANY(3),
    EXTERNAL_HAJJ_COMPANY(4),
    SERVICE_GROUP(5);


    private final long id;

    ECompanyType(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public static ECompanyType fromId(long id) {
        List<ECompanyType> result = Arrays.stream(ECompanyType.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (ECompanyType) CollectionUtils.get(result, 0);
    }
}
