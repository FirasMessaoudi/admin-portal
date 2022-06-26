/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.complaint;

import lombok.*;
import org.springframework.util.MultiValueMap;

/**
 * Web service operation result
 *
 * @author othman alamoud
 * @since 1.2.6 */
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CrmAuthResponse {

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
    private String token;
    private Integer responseCode;
    private String message;
}
