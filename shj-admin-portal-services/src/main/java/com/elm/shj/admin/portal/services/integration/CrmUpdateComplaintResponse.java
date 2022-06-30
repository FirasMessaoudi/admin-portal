/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.integration;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Web service operation result
 *
 * @author othman alamoud
 * @since 1.2.6 */
@Getter
@Setter
@Builder
public class CrmUpdateComplaintResponse {

    public enum ECrmResponseStatus {
        SUCCESS(200), FAILURE(400);

        int code;

        ECrmResponseStatus(int code) {
            this.code = code;
        }

        int getCode() {
            return code;
        }
    }
    private String crmTicketNumber;
    private Integer responseCode;
    private String message;
}
