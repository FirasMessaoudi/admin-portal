/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.data.segment.DataSegmentService;
import com.elm.shj.admin.portal.services.dto.DataRequestDto;
import com.elm.shj.admin.portal.services.dto.DataSegmentDto;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Validator for {@link DataSegmentStructure} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class DataSegmentStructureValidator implements ConstraintValidator<DataSegmentStructure, Object> {

    private static final String XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    private static final String MSG_DATA_SEGMENT_MISSING = "validation.constraints.data-segment.missing";
    private static final String MSG_DATA_SEGMENT_NOT_FOUND = "validation.constraints.data-segment.not-found";
    private static final String MSG_DATA_SEGMENT_TPL_NOT_FOUND = "validation.constraints.data-segment.template-not-found";
    private static final String MSG_EXCEL_ONLY = "validation.constraints.data-segment.excel-only";
    private static final String MSG_EMPTY_FILE = "validation.constraints.data-segment.empty-file";
    private static final String MSG_INVALID_DATA_STRUCTURE = "validation.constraints.data-segment.invalid-data-structure";

    @Autowired
    private DataSegmentService dataSegmentService;

    @Autowired
    private MultipartHttpServletRequest request;

    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        // disable existing violation message
        context.disableDefaultConstraintViolation();
        // check type and content
        if (!(value instanceof DataRequestDto) || ((DataRequestDto) value).getDataSegment() == null) {
            // build new violation message and add it
            context.buildConstraintViolationWithTemplate(MSG_DATA_SEGMENT_MISSING).addConstraintViolation();
            return false;
        }
        DataRequestDto dataRequest = (DataRequestDto) value;
        DataSegmentDto dataSegment = dataSegmentService.findOne(dataRequest.getDataSegment().getId());
        // check data segment id exists in database
        if (dataSegment == null) {
            // build new violation message and add it
            context.buildConstraintViolationWithTemplate(MSG_DATA_SEGMENT_NOT_FOUND).addConstraintViolation();
            return false;
        }
        // check data segment template file exists
        Resource templateFile = dataSegmentService.loadTemplateFile(dataSegment);
        if (!templateFile.exists()) {
            // build new violation message and add it
            context.buildConstraintViolationWithTemplate(MSG_DATA_SEGMENT_TPL_NOT_FOUND).addConstraintViolation();
            return false;
        }
        // retrieve uploaded file
        MultipartFile file = new StandardMultipartHttpServletRequest(request).getFile("file");
        if (file == null) {
            // build new violation message and add it
            context.buildConstraintViolationWithTemplate(MSG_EMPTY_FILE).addConstraintViolation();
            return false;
        }
        // check content type
        if (!XLSX_CONTENT_TYPE.equals(file.getContentType())) {
            // build new violation message and add it
            context.buildConstraintViolationWithTemplate(MSG_EXCEL_ONLY).addConstraintViolation();
            return false;
        }
        // read header columns from data segment template sheet
        List<String> templateHeaders = readHeaderColumns(templateFile.getInputStream());
        // read header columns from uploaded file sheet
        List<String> uploadedFileHeaders = readHeaderColumns(file.getInputStream());
        // Check whether the uploaded file has the expected structure
        if (!templateHeaders.equals(uploadedFileHeaders)) {
            // build new violation message and add it
            context.buildConstraintViolationWithTemplate(MSG_INVALID_DATA_STRUCTURE).addConstraintViolation();
            return false;
        }
        return true;
    }

    /**
     * Reads the header columns
     *
     * @param is          the input stream coming with the request
     * @return the header column names
     * @throws IOException if any exception happens during reading the stream coming with the request
     */
    private List<String> readHeaderColumns(InputStream is) throws IOException {
        ArrayList<String> headerColumns = new ArrayList<>();
        // load workbook
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        // read first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
        // read first row
        int headerRowNum = sheet.getFirstRowNum();
        XSSFRow row = sheet.getRow(headerRowNum);
        // iterate over cells and read header columns
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            headerColumns.add(cell.getStringCellValue());
        }
        // close workbook
        workbook.close();
        // return the result
        return headerColumns;
    }

}
