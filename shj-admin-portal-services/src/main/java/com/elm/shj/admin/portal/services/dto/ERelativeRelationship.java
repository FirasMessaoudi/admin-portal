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
    FATHER(1), MOTHER(2), SON(3), DAUGHTER(4), BROTHER(5),
    SISTER(6), HUSBAND(7), WIFE(8), RELATIVE(9), COMPANION(10);

    private final int id;

    ERelativeRelationship(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ERelativeRelationship fromId(long id) {
        List<ERelativeRelationship> result = Arrays.stream(ERelativeRelationship.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (ERelativeRelationship) CollectionUtils.get(result, 0);
    }

}
