/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.integration;

import lombok.Builder;
import lombok.Data;

/**
 * Web service operation result
 *
 * @author Aymen Dhaoui
 * @since 1.1.0
 */
@Data
@Builder
public class WsResponse<T> {

    public enum EWsResponseStatus {
        SUCCESS(0), FAILURE(1);

        int code;

        EWsResponseStatus(int code) {
            this.code = code;
        }

        int getCode() {
            return code;
        }
    }

    private EWsResponseStatus status = EWsResponseStatus.SUCCESS;
    private T body;
}
