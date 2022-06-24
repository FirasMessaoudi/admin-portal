/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Complaint Update CRM object.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserCRMDto implements Serializable {

    private static final long serialVersionUID = 5702033825414255272L;

        String timestamp;
        DataCMRResponse data;
        Boolean status;
        Object error;
        String exceptionDetails;

    static class DataCMRResponse {
        String message;
    }
}




