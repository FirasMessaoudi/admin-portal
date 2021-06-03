/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.processor;

import com.elm.shj.admin.portal.services.data.reader.ExcelItemReader;
import com.elm.shj.admin.portal.services.data.reader.ExcelItemReaderFactory;
import com.elm.shj.admin.portal.services.data.validators.DataValidationResult;
import com.elm.shj.admin.portal.services.dto.DataSegmentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * A processor for different data requests
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DataProcessorService {

    private final MessageSource messageSource;
    private final Validator validator;
    private final ExcelItemReaderFactory excelItemReaderFactory;

    /**
     * Processes the data request file using the provided mapper to extract items of type <T>
     *
     * @param requestFile the request file resource
     * @param dataSegment the request data segment
     * @param <T>         the type of the items to be parsed
     * @return the result of the processing
     * @throws IOException in case of any IO failure
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public <T> DataProcessorResult<T> processRequestFile(Resource requestFile, DataSegmentDto dataSegment) throws IOException {
        // create suitable reader;
        ExcelItemReader<T> excelItemReader = excelItemReaderFactory.create(dataSegment);
        // load workbook
        XSSFWorkbook workbook = new XSSFWorkbook(requestFile.getInputStream());
        // read first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
        // read first row
        int headerRowNum = sheet.getFirstRowNum();
        List<DataValidationResult> dataValidationResults = new ArrayList<>();
        List<T> parsedItems = new ArrayList<>();
        StreamSupport.stream(Spliterators.spliteratorUnknownSize(sheet.rowIterator(), Spliterator.ORDERED), false).forEach(row -> {
            // skip header row
            if (row.getRowNum() != headerRowNum && !isBlankRow(row)) {
                try {
                    // read item
                    T item = excelItemReader.read(row);
                    // add all reading errors
                    dataValidationResults.addAll(excelItemReader.getDataReadingErrors());
                    // run validations
                    Set<ConstraintViolation<T>> violations = validator.validate(item);
                    if (violations.isEmpty()) {
                        // if no validation errors than add item
                        parsedItems.add(item);
                    } else {
                        // otherwise add errors
                        violations.forEach(v -> dataValidationResults.add(DataValidationResult.builder().valid(false).cell(excelItemReader.findCellByPropertyName(row, v.getPropertyPath().toString())).errorMessages(Collections.singletonList(v.getMessage())).valid(false).build()));
                    }
                } catch (Exception e) {
                    ReflectionUtils.handleReflectionException(e);
                }
            }
        });
        // return result
        DataProcessorResult<T> result = new DataProcessorResult<>();
        result.setDataValidationResults(dataValidationResults);
        result.setParsedItems(parsedItems);
        result.setWithErrors(dataValidationResults.stream().anyMatch(dvr -> !dvr.isValid()));
        return result;
    }

    /**
     * Generates the error file based on the original file
     *
     * @param originalFile          the original data request file resource
     * @param dataValidationResults the validation result after processing the original file
     * @return the error file resource
     * @throws IOException in case of any IO failure
     */
    public Resource generateErrorFile(Resource originalFile, List<DataValidationResult> dataValidationResults) throws IOException {
        // load workbook
        XSSFWorkbook workbook = new XSSFWorkbook(originalFile.getInputStream());
        // read first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
        // extract rows with errors
        Set<Integer> rowsWithErrors = dataValidationResults.stream().filter(dvr -> !dvr.isValid()).map(dvr -> dvr.getCell().getRow().getRowNum()).collect(Collectors.toSet());
        // highlight them
        highlightErrorRows(workbook, sheet, rowsWithErrors);
        // add comment to cells with errors
        for (DataValidationResult dataValidationResult : dataValidationResults) {
            if (!dataValidationResult.isValid()) {
                addCommentAndHighlight(workbook, sheet, dataValidationResult.getCell().getAddress(), String.join("\n", dataValidationResult.getErrorMessages()));
            }
        }
        // Write the output to a byte array
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        out.close();
        // Closing the workbook
        workbook.close();
        // return resource with byte array
        return new ByteArrayResource(out.toByteArray());
    }

    /**
     * Highlights all rows having errors
     *
     * @param wb             the current workbook
     * @param sheet          the current sheet
     * @param rowsWithErrors the rows list with errors
     */
    private void highlightErrorRows(Workbook wb, Sheet sheet, Set<Integer> rowsWithErrors) {
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = wb.createFont();
        font.setColor(IndexedColors.BROWN.getIndex());
        style.setFont(font);
        rowsWithErrors.forEach(rowNum -> {
            Row row = sheet.getRow(rowNum);
            StreamSupport.stream(Spliterators.spliteratorUnknownSize(row.cellIterator(), Spliterator.ORDERED), false)
                    .forEach(cell -> cell.setCellStyle(style));
        });

    }

    /**
     * Adds comment and highlights cell with errors
     *
     * @param wb          the current workbook
     * @param sheet       the current sheet
     * @param cellAddress the cell address
     * @param comment     the comment containing the error message
     */
    private void addCommentAndHighlight(Workbook wb, Sheet sheet, CellAddress cellAddress, String comment) {

        Row row = sheet.getRow(cellAddress.getRow());
        Cell cell = row.getCell(cellAddress.getColumn());

        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.RED.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.RED.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.RED.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.RED.getIndex());


        CreationHelper factory = wb.getCreationHelper();
        String commentText = messageSource.getMessage(comment, null, Locale.forLanguageTag("en")) + "\n" + messageSource.getMessage(comment, null, Locale.forLanguageTag("ar"));
        if(cell.getCellComment() != null && cell.getCellComment().getString() != null) {
            cell.getCellComment().setString(factory.createRichTextString(commentText + "\n--------------------\n" + cell.getCellComment().getString().getString()));
        } else {
            Drawing<?> drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = factory.createClientAnchor();
            anchor.setCol1(cell.getColumnIndex());
            anchor.setCol2(cell.getColumnIndex() + 3);
            anchor.setRow1(cell.getRow().getRowNum());
            anchor.setRow2(cell.getRow().getRowNum() + 6);
            Comment cellComment = drawing.createCellComment(anchor);
            cellComment.setString(factory.createRichTextString(commentText));
            cell.setCellComment(cellComment);
            cell.setCellStyle(style);
        }
    }

    /**
     * Checks if the row is blank - meaning that all cells are blank
     *
     * @param row the row to check
     * @return if the row is blank
     */
    private boolean isBlankRow(Row row) {
        AtomicBoolean isBlank = new AtomicBoolean(true);
        StreamSupport.stream(Spliterators.spliteratorUnknownSize(row.cellIterator(), Spliterator.ORDERED), false).forEach(cell -> {
            if (cell.getCellType() != CellType.BLANK) {
                isBlank.set(false);
            }
        });
        return isBlank.get();
    }

}
