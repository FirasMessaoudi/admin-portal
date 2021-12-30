/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Integration error
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
@Builder
public class WsError {

    private EWsError error = EWsError.GENERIC;
    private String referenceNumber;
    public enum EWsError {
        GENERIC(100),
        APPLICANT_NOT_FOUND(101),
        APPLICANT_NOT_MATCHED(102),
        CARD_DETAILS_NOT_FOUND(103),
        UPDATE_APPLICANT_FAILED(104),
        COMPANY_STAFF_NOT_FOUND(105),
        APPLICANT_CHAT_CONTACT_NOT_FOUND(106),
        APPLICANT_CHAT_CONTACT_ALREADY_EXIST(107),
        INCIDENT_TYPE_NOT_FOUND(108),
        APPLICANT_RITUAL_NOT_FOUND(109);
        int code;

        EWsError(int code) {
            this.code = code;
        }

        int getCode() {
            return code;
        }
    }
}
