/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.reader;

import com.elm.shj.admin.portal.services.data.mapper.ApplicantMapper;
import com.elm.shj.admin.portal.services.data.mapper.IRowMapper;
import com.elm.shj.admin.portal.services.data.validators.DataValidationResult;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Item reader that reads from Excel file
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class ExcelItemReader<T> {
    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";

    private final DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    private final NumberFormat numberFormat = NumberFormat.getInstance();
    private final IRowMapper<T> rowMapper;
    private Class<T> typeClass;

    @SuppressWarnings("unchecked")
    public ExcelItemReader(IRowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
        try {
            this.typeClass = (Class<T>) Class.forName(((ParameterizedType) rowMapper.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0].getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        ExcelItemReader<ApplicantDto> excelItemReader = new ExcelItemReader<>(new ApplicantMapper());

        // load workbook
        XSSFWorkbook workbook = new XSSFWorkbook(new File("F:\\dev\\apps\\sftp\\data\\smart-hajj\\data-uploads\\2021_05_10\\DS-OMX9HG531\\applicant-data_20210510_000133.xlsx"));
        // read first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
        // read first row
        int headerRowNum = sheet.getFirstRowNum();
        List<DataValidationResult> dataValidationResults = new ArrayList<>();
        StreamSupport.stream(Spliterators.spliteratorUnknownSize(sheet.rowIterator(), Spliterator.ORDERED), false).forEach(row -> {
            // skip header row
            if (row.getRowNum() != headerRowNum) {
                try {
                    excelItemReader.read(row);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * Reads an item from excel row
     *
     * @param row the row the read
     * @return the read item
     */
    @SuppressWarnings("rawtypes")
    public T read(Row row) throws ReflectiveOperationException {
        Map<Integer, String> mapping = rowMapper.mapRow(row);
        // create new instance of the type to be populated
        T type = createInstance(typeClass);
        Set<Field> nestedFields = mapping.values().stream().filter(f -> f.contains(".")).map(f -> {
            try {
                return typeClass.getDeclaredField(f.split("\\.")[0]);
            } catch (NoSuchFieldException e) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toSet());

        // filter nested fields and create instances for them based on their types
        nestedFields.forEach(f -> {
            ReflectionUtils.makeAccessible(f);
            try {
                if (f.getType().isAssignableFrom(List.class)) {
                    ReflectionUtils.setField(f, type, Lists.newArrayList(createInstance(Class.forName(((AnnotatedParameterizedType) f.getAnnotatedType()).getAnnotatedActualTypeArguments()[0].getType().getTypeName()))));
                } else if (f.getType().isArray()) {
                    ReflectionUtils.setField(f, type, Lists.newArrayList(createInstance(Class.forName(((AnnotatedParameterizedType) f.getAnnotatedType()).getAnnotatedActualTypeArguments()[0].getType().getTypeName()))).toArray());
                } else {
                    ReflectionUtils.setField(f, type, createInstance(f.getType()));
                }
            } catch (ClassNotFoundException e) {
                ReflectionUtils.handleReflectionException(e);
            }
        });

        // set fields from cell values based on field types
        mapping.forEach((cellIndex, fieldName) -> {
            Field nestedField, fieldToProcess;
            Object target = type;
            try {
                // start by nested fields
                if (fieldName.contains(".")) {
                    String nestedFieldName = fieldName.split("\\.")[0];
                    String nestedFieldAttributeName = fieldName.split("\\.")[1];
                    nestedField = typeClass.getDeclaredField(nestedFieldName);
                    fieldToProcess = ReflectionUtils.findField(Class.forName(((AnnotatedParameterizedType) nestedField.getAnnotatedType()).getAnnotatedActualTypeArguments()[0].getType().getTypeName()), nestedFieldAttributeName);
                    ReflectionUtils.makeAccessible(nestedField);
                    if (nestedField.getType().isAssignableFrom(List.class)) {
                        target = ((List) Objects.requireNonNull(ReflectionUtils.getField(nestedField, type))).get(0);
                    } else if (nestedField.getType().isArray()) {
                        target = Array.get(ReflectionUtils.getField(nestedField, type), 0);
                    } else {
                        target = ReflectionUtils.getField(nestedField, type);
                    }
                } else {
                    fieldToProcess = ReflectionUtils.findField(typeClass, fieldName);
                }
                assert fieldToProcess != null;
                ReflectionUtils.makeAccessible(fieldToProcess);
                fieldToProcess.set(target, readCellValue(row.getCell(row.getFirstCellNum() + cellIndex), fieldToProcess));
            } catch (ReflectiveOperationException e) {
                ReflectionUtils.handleReflectionException(e);
            }
        });

        // return the populated instance
        return type;
    }

    /**
     * Creates an instance of a class using default constructor
     *
     * @param clazz the class the create instance for
     * @return the created instance
     */
    private <B> B createInstance(Class<B> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            ReflectionUtils.handleReflectionException(e);
            return null;
        }
    }

    /**
     * Reads the cell value based on the field type
     *
     * @param cell  the cell to read value from
     * @param field the field to set the cell value to
     * @return the cell value based on the field type
     */
    private Object readCellValue(Cell cell, Field field) {
        if (field.getType().isAssignableFrom(String.class)) {
            return readCellValueAsString(cell);
        } else if (field.getType().isAssignableFrom(Date.class)) {
            return readCellValueAsDate(cell);
        } else if (field.getType().isPrimitive()) {
            switch (field.getType().getName().charAt(0)) {
                case 'b':
                    return readCellValueAsNumber(cell, byte.class);
                case 's':
                    return readCellValueAsNumber(cell, short.class);
                case 'i':
                    return readCellValueAsNumber(cell, int.class);
                case 'l':
                    return readCellValueAsNumber(cell, long.class);
                case 'f':
                    return readCellValueAsNumber(cell, float.class);
                case 'd':
                    return readCellValueAsNumber(cell, double.class);
                default:
                    return -1;
            }
        } else if (field.getType().isAssignableFrom(Number.class)) {
            switch (field.getType().getName().charAt(0)) {
                case 'B':
                    return readCellValueAsNumber(cell, Byte.class);
                case 'S':
                    return readCellValueAsNumber(cell, Short.class);
                case 'I':
                    return readCellValueAsNumber(cell, Integer.class);
                case 'L':
                    return readCellValueAsNumber(cell, Long.class);
                case 'F':
                    return readCellValueAsNumber(cell, Float.class);
                case 'D':
                    return readCellValueAsNumber(cell, Double.class);
                default:
                    return -1;
            }
        } else if (field.getType().isAssignableFrom(Boolean.class)) {
            return readCellValueAsBoolean(cell);
        } else {
            return null;
        }
    }

    /**
     * Reads the cell value as string
     *
     * @param cell the cell to read value from
     * @return the cell value as string
     */
    public String readCellValueAsString(Cell cell) {
        Object value = readRawCellValue(cell);
        if (value == null) {
            return null;
        } else if (value instanceof Number) {
            return numberFormat.format(((Number) value).doubleValue());
        } else if (value instanceof Date) {
            return dateFormat.format((Date) value);
        } else {
            return value.toString();
        }
    }

    /**
     * Reads the cell value as boolean
     *
     * @param cell the cell to read value from
     * @return the cell value as boolean
     */
    public Boolean readCellValueAsBoolean(Cell cell) {
        Object value = readRawCellValue(cell);
        if (value == null) {
            return null;
        } else if (value instanceof Number) {
            return BooleanUtils.toBoolean((Integer) value);
        } else if (value instanceof String) {
            return BooleanUtils.toBoolean((String) value);
        } else {
            throw ExcelItemReaderException.builder().cell(cell).errorType(ExcelItemReaderException.EExcelItemReaderErrorType.INVALID_BOOLEAN_FORMAT).build();
        }
    }

    /**
     * Reads the cell value as number
     *
     * @param cell the cell to read value from
     * @return the cell value as number
     */
    public <N extends Number> Object readCellValueAsNumber(Cell cell, Class<N> clazz) {
        Object value = readRawCellValue(cell);
        if (value == null) {
            return clazz.isPrimitive() ? 0 : null;
        } else if (value instanceof Number) {
            if (clazz.isPrimitive()) {
                switch (clazz.getName().charAt(0)) {
                    case 'b':
                        return ((Number) value).byteValue();
                    case 's':
                        return ((Number) value).shortValue();
                    case 'i':
                        return ((Number) value).intValue();
                    case 'l':
                        return ((Number) value).longValue();
                    case 'f':
                        return ((Number) value).floatValue();
                    case 'd':
                        return ((Number) value).doubleValue();
                    default:
                        return 0;
                }
            } else {
                return value;
            }
        } else if (value instanceof String) {
            try {
                return numberFormat.parse((String) value);
            } catch (ParseException e) {
                throw ExcelItemReaderException.builder().cell(cell).errorType(ExcelItemReaderException.EExcelItemReaderErrorType.INVALID_NUMBER_FORMAT).build();
            }
        } else {
            throw ExcelItemReaderException.builder().cell(cell).errorType(ExcelItemReaderException.EExcelItemReaderErrorType.INVALID_NUMBER_FORMAT).build();
        }
    }

    /**
     * Reads the cell value as date
     *
     * @param cell the cell to read value from
     * @return the cell value as date
     */
    public Date readCellValueAsDate(Cell cell) {
        Object value = readRawCellValue(cell);
        if (value == null) {
            return null;
        } else if (value instanceof Date) {
            return (Date) value;
        } else if (value instanceof String) {
            try {
                return dateFormat.parse((String) value);
            } catch (ParseException e) {
                throw ExcelItemReaderException.builder().cell(cell).errorType(ExcelItemReaderException.EExcelItemReaderErrorType.INVALID_DATE_FORMAT).build();
            }
        } else {
            throw ExcelItemReaderException.builder().cell(cell).errorType(ExcelItemReaderException.EExcelItemReaderErrorType.INVALID_DATE_FORMAT).build();
        }
    }


    /**
     * Reads the raw cell value
     *
     * @param cell the cell to read value from
     * @return the raw cell value
     */
    private Object readRawCellValue(Cell cell) {
        if (cell == null)
            return null;
        switch (cell.getCellType()) {
            case STRING:
                RichTextString rts = cell.getRichStringCellValue();
                if (rts != null) {
                    return rts.getString();
                }
                return null;
            case NUMERIC:
                String value = cell.toString();
                /*
                 * In POI we cannot know which cell is date or number because both
                 * cells have numeric type To fix this problem we need to call
                 * toString if it's number cell we can parse it but if it's date
                 * cell we cannot parse the value with number parser
                 */
                try {
                    numberFormat.parse(value);
                    return new BigDecimal(value);
                } catch (Exception e) {
                    return cell.getDateCellValue();
                }
            case BLANK:
                return null;
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
        }
        return null;
    }
}
