/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.reader;


/**
 * Enum representing Errors while reading an items from excel file
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public enum EExcelItemReaderErrorType {
    INVALID_DATE_FORMAT("validation.data.constraints.msg.123"),
    INVALID_NUMBER_FORMAT("validation.data.constraints.msg.123"),
    INVALID_BOOLEAN_FORMAT("validation.data.constraints.msg.123"),
    DUPLICATE_VALUE("validation.data.constraints.msg.129");

    String message;

    EExcelItemReaderErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
