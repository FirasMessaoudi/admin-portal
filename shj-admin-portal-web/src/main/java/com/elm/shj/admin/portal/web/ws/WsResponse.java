/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.MultiValueMap;

/**
 * Web service operation result
 *
 * @author Aymen Dhaoui
 * @since 1.1.0
 */
@Getter
@Setter
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

    private int status = EWsResponseStatus.SUCCESS.getCode();
    private T body;
    private MultiValueMap<String, String> headers;
}
