/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.reader;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Exception thrown while reading an item from excel file
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Data
@Builder
@RequiredArgsConstructor
public class ExcelItemReaderException extends RuntimeException {

    public enum EExcelItemReaderErrorType {
        INVALID_DATE_FORMAT, INVALID_NUMBER_FORMAT, INVALID_BOOLEAN_FORMAT
    }

    private Cell cell;
    private EExcelItemReaderErrorType errorType;
}
