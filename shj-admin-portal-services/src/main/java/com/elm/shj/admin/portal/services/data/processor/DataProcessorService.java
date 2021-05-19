/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.processor;

import com.elm.shj.admin.portal.services.data.mapper.AbstractRowMapper;
import com.elm.shj.admin.portal.services.data.mapper.ApplicantRowMapper;
import com.elm.shj.admin.portal.services.data.mapper.DataSegmentMappingRegistry;
import com.elm.shj.admin.portal.services.data.validators.AbstractDataValidator;
import com.elm.shj.admin.portal.services.data.validators.ApplicantExistsValidator;
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

import javax.script.ScriptException;
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

    private final DataSegmentMappingRegistry dataSegmentMappingRegistry;
    private final MessageSource messageSource;
    private final ApplicantExistsValidator applicantExistsValidator;

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
        AbstractRowMapper mapper = dataSegmentMappingRegistry.mapperOf(dataSegment);
        // load workbook
        XSSFWorkbook workbook = new XSSFWorkbook(requestFile.getInputStream());
        // read first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
        // read first row
        int headerRowNum = sheet.getFirstRowNum();
        List<DataValidationResult> dataValidationResults = new ArrayList<>();
        StreamSupport.stream(Spliterators.spliteratorUnknownSize(sheet.rowIterator(), Spliterator.ORDERED), false).forEach(row -> {
            // skip header row
            if (row.getRowNum() != headerRowNum && !isBlankRow(row)) {
                // loop over mapped validators map
                mapper.mapValidators(row).forEach((cell, validators) -> {
                    // loop over validators
                    ((List<AbstractDataValidator>)validators).forEach(validator -> {
                        log.debug("validating row#" + row.getRowNum() + " cell#" + ((Cell)cell).getColumnIndex());
                        dataValidationResults.add(validator.validate((Cell)cell));
                    });
                });
            }
        });

        List<T> parsedItems = new ArrayList<>();
        // loop over successful rows and map the row
        dataValidationResults.stream()
                .filter(DataValidationResult::isValid)
                .map(dvr -> dvr.getCell().getRow().getRowNum())
                .collect(Collectors.toSet())
                .forEach(successRowNum -> parsedItems.add((T) mapper.mapRow(sheet.getRow(successRowNum))));

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

        Drawing<?> drawing = sheet.createDrawingPatriarch();
        CreationHelper factory = wb.getCreationHelper();
        ClientAnchor anchor = factory.createClientAnchor();
        anchor.setCol1(cell.getColumnIndex());
        anchor.setCol2(cell.getColumnIndex() + 3);
        anchor.setRow1(cell.getRow().getRowNum());
        anchor.setRow2(cell.getRow().getRowNum() + 4);
        Comment cellComment = drawing.createCellComment(anchor);
        cellComment.setString(factory.createRichTextString(messageSource.getMessage(comment, null, Locale.forLanguageTag("en")) + "\n" + messageSource.getMessage(comment, null, Locale.forLanguageTag("ar"))));
        cell.setCellComment(cellComment);
        cell.setCellStyle(style);
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

    public static void main(String[] args) throws ScriptException, IOException {
        /*ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String foo = "(40+2) > 80";
        System.out.println(engine.eval(foo));*/
    }

}
