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
 * @author Ahmed Ali
 * @since 1.1.0
 */
public enum ECompanyType {
    ESTABLISHMENT(1),
    MISSION(2),
    INTERNAL_HAJ_COMPANY(3),
    EXTERNAL_HAJ_COMPANY(4),
    SERVICE_GROUP(5),
    GOVERNMENT_AGENCY(6);


    private final int id;

    ECompanyType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ECompanyType fromId(int id) {
        List<ECompanyType> result = Arrays.stream(ECompanyType.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (ECompanyType) CollectionUtils.get(result, 0);
    }
}
