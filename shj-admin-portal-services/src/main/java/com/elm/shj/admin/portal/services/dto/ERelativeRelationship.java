/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enum for Relative Relationship
 *
 * @author salzoubi
 * @since 1.1.0
 */
public enum ERelativeRelationship {
    MOTHER(1), FATHER(2), WIFE(3), HUSBAND(4), SON(5),
    DAUGHTER(6), BROTHER(7), SISTER(8), RELATIVE(9), COMPANION(10);

    private final int id;

    ERelativeRelationship(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ERelativeRelationship fromId(int id) {
        List<ERelativeRelationship> result = Arrays.stream(ERelativeRelationship.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (ERelativeRelationship) CollectionUtils.get(result, 0);
    }

}
