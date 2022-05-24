/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

/**
 * Enum for main collection statuses
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public enum ECollectionStatus {

    NEW(1), FAIL_TO_GENERATE(2), GENERATING_CARDS(3), READY(4);

    private final int id;

    ECollectionStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
