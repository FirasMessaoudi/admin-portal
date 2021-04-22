/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

/**
 * Enum for data request statuses
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public enum EDataRequestStatus {

    NEW(1), UNDER_PROCESSING(2), PROCESSED(3), PROCESSED_WITH_ERRORS(3), CANCELLED(5);

    private final int id;

    EDataRequestStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
