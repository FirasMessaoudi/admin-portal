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
    FIELD_REQUIRED("validation.data.constraints.msg.20001"),
    INVALID_DATE_FORMAT("validation.data.constraints.msg.20005"),
    INVALID_NUMBER_FORMAT("validation.data.constraints.msg.20005"),
    INVALID_BOOLEAN_FORMAT("validation.data.constraints.msg.20005"),
    DUPLICATE_VALUE("validation.data.constraints.msg.30001"),
    NOT_SAME_VALUE("validation.data.constraints.msg.30004"),
    NOT_GROUP_WITH_DIFFERENT_LEADERS("validation.data.constraints.msg.30005"),
    NOT_RITUAL_TYPE_FOUND("validation.data.constraints.msg.30019"),
    NOT_APPLICANT_GROUP_FOUND("validation.data.constraints.msg.30021"),
    NOT_APPLICANT_FOUND("validation.data.constraints.msg.20015"),
    APPLICANT_PACKAGE_NOT_FOUND("validation.data.constraints.msg.30023"),
    PACKAGE_HOUSING_NOT_FOUND("validation.data.constraints.msg.30024"),
    INVALID_ID_NUMBER("validation.data.constraints.msg.30025"),
    STAFF_ID_ALREADY_EXITS_IN_COMPANY("validation.data.constraints.msg.30026");

    String message;

    EExcelItemReaderErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
