/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

/**
 * Enum for Relative Relationship
 *
 * @author salzoubi
 * @since 1.1.0
 */
public enum ERelativeRelationship {
    FATHER(1), MOTHER(2), SON(3), DAUGHTER(4), BROTHER(5),
    SISTER(6), HUSBAND(7), WIFE(8),RELATIVE(9),COMPANION(10);

    private final int id;

    ERelativeRelationship(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }


}
