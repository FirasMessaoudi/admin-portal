/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.reader;

import com.elm.shj.admin.portal.services.data.mapper.CellIndex;
import com.elm.shj.admin.portal.services.data.mapper.NestedCells;
import com.elm.shj.admin.portal.services.data.validators.DataValidationResult;
import com.elm.shj.admin.portal.services.data.validators.UniquePerRequest;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Item reader that reads from Excel file
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
public class ExcelItemReader<T> {
    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";

    private final DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    private final NumberFormat numberFormat = NumberFormat.getInstance();
    private final Map<Integer, String> fieldMapping = new HashMap<>();
    private final Class<T> typeClass;
    private final Map<Field, List<Object>> uniqueFields = new HashMap<>();
    private final List<DataValidationResult> dataReadingErrors = new ArrayList<>();

    public ExcelItemReader(Class<T> typeClass) {
        this.typeClass = typeClass;

        initMapping(typeClass, null);

        Arrays.stream(typeClass.getDeclaredFields()).filter(f ->
                f.isAnnotationPresent(NestedCells.class)
        ).forEach(f -> {
            String className;
            if (f.getType().isAssignableFrom(List.class) || f.getType().isAssignableFrom(Set.class) || f.getType().isArray()) {
                className = ((AnnotatedParameterizedType) f.getAnnotatedType()).getAnnotatedActualTypeArguments()[0].getType().getTypeName();
            } else {
                className = f.getType().getName();
            }
            try {
                initMapping(Class.forName(className), f.getName());
            } catch (ClassNotFoundException e) {
                ReflectionUtils.handleReflectionException(e);
            }
        });

    }

    public Class<T> getTypeClass() {
        return typeClass;
    }

    public List<DataValidationResult> getDataReadingErrors() {
        return dataReadingErrors;
    }

    /**
     * Finds the cell mapped for the given property name
     *
     * @param row          the row the fetch the cell from
     * @param propertyName the property name mapped with the cell
     * @return the cell mapped for the given property name
     */
    @SuppressWarnings("ConstantConditions")
    public Cell findCellByPropertyName(Row row, String propertyName) {
        if (fieldMapping.values().stream().anyMatch(n -> n.equals(propertyName))) {
            return row.getCell(row.getFirstCellNum() + getKey(fieldMapping, propertyName));
        }
        return row.getCell(row.getFirstCellNum());
    }

    /**
     * Utility class to get the key based on the value from a given map
     *
     * @param map   the map
     * @param value the value to search the key for
     * @param <K>   the key type
     * @param <V>   the value type
     * @return the key for the given value if found or else null
     */
    private <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Adds mapping for fields of the type passed in parameter
     *
     * @param clazz           the type to map from
     * @param parentFieldName if the mapping is for a nested field
     */
    private void initMapping(Class<?> clazz, String parentFieldName) {

        uniqueFields.putAll(Arrays.stream(clazz.getDeclaredFields()).filter(f ->
                f.isAnnotationPresent(CellIndex.class) && f.isAnnotationPresent(UniquePerRequest.class)
        ).collect(Collectors.toMap((f) -> f, (f) -> new ArrayList<>())));

        fieldMapping.putAll(Arrays.stream(clazz.getDeclaredFields()).filter(f ->
                f.isAnnotationPresent(CellIndex.class)
        ).collect(Collectors.toMap((f) -> f.getAnnotation(CellIndex.class).index(), (f) -> (StringUtils.isNoneBlank(parentFieldName) ? parentFieldName + "[0]." : "") + f.getName())));
    }

    /**
     * Reads an item from excel row
     *
     * @param row the row the read
     * @return the read item
     */
    @SuppressWarnings("rawtypes")
    public T read(Row row) throws ReflectiveOperationException {
        // create new instance of the type to be populated
        T type = createInstance(typeClass);
        Set<Field> nestedFields = fieldMapping.values().stream().filter(f -> f.contains(".")).map(f -> {
            try {
                return typeClass.getDeclaredField(f.split("\\[0]\\.")[0]);
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
        fieldMapping.forEach((cellIndex, fieldName) -> {
            Field nestedField, fieldToProcess;
            Object target = type;
            try {
                // start by nested fields
                if (fieldName.contains(".")) {
                    String nestedFieldName = fieldName.split("\\[0]\\.")[0];
                    String nestedFieldAttributeName = fieldName.split("\\[0]\\.")[1];
                    nestedField = typeClass.getDeclaredField(nestedFieldName);
                    ReflectionUtils.makeAccessible(nestedField);
                    if (nestedField.getType().isAssignableFrom(List.class)) {
                        target = ((List) Objects.requireNonNull(ReflectionUtils.getField(nestedField, type))).get(0);
                        fieldToProcess = ReflectionUtils.findField(Class.forName(((AnnotatedParameterizedType) nestedField.getAnnotatedType()).getAnnotatedActualTypeArguments()[0].getType().getTypeName()), nestedFieldAttributeName);
                    } else if (nestedField.getType().isArray()) {
                        target = Array.get(ReflectionUtils.getField(nestedField, type), 0);
                        fieldToProcess = ReflectionUtils.findField(Class.forName(((AnnotatedParameterizedType) nestedField.getAnnotatedType()).getAnnotatedActualTypeArguments()[0].getType().getTypeName()), nestedFieldAttributeName);
                    } else {
                        target = ReflectionUtils.getField(nestedField, type);
                        fieldToProcess = ReflectionUtils.findField(nestedField.getType(), nestedFieldAttributeName);
                    }
                } else {
                    fieldToProcess = ReflectionUtils.findField(typeClass, fieldName);
                }
                assert fieldToProcess != null;
                ReflectionUtils.makeAccessible(fieldToProcess);
                Cell cell = row.getCell(row.getFirstCellNum() + cellIndex);
                Object value = readCellValue(cell, fieldToProcess);
                fieldToProcess.set(target, value);
                if (value != null && StringUtils.isNotBlank(value.toString()) && uniqueFields.containsKey(fieldToProcess)) {
                    if (uniqueFields.get(fieldToProcess).contains(value)) {
                        dataReadingErrors.add(DataValidationResult.builder().valid(false).cell(cell).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.DUPLICATE_VALUE.getMessage())).valid(false).build());
                    } else {
                        uniqueFields.get(fieldToProcess).add(value);
                    }
                }
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
        switch (field.getType().getSimpleName().charAt(0)) {
            case 'b':
            case 'B':
                if (field.getType().getSimpleName().charAt(1) == 'y')
                    return readCellValueAsNumber(cell, field.getType().getSimpleName().charAt(0) == 'B' ? Byte.class : byte.class);
                else if (field.getType().getSimpleName().charAt(1) == 'o')
                    return readCellValueAsBoolean(cell);
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
            case 'S':
                if (field.getType().getSimpleName().charAt(1) == 'h')
                    return readCellValueAsNumber(cell, Short.class);
                else if (field.getType().getSimpleName().charAt(1) == 't')
                    return readCellValueAsString(cell);
            case 'I':
                return readCellValueAsNumber(cell, Integer.class);
            case 'L':
                return readCellValueAsNumber(cell, Long.class);
            case 'F':
                return readCellValueAsNumber(cell, Float.class);
            case 'D':
                if (field.getType().getSimpleName().charAt(1) == 'o')
                    return readCellValueAsNumber(cell, Double.class);
                else if (field.getType().getSimpleName().charAt(1) == 'a')
                    return readCellValueAsDate(cell);
            default:
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
            dataReadingErrors.add(DataValidationResult.builder().valid(false).cell(cell).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.INVALID_BOOLEAN_FORMAT.getMessage())).valid(false).build());
            return false;
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
            return clazz.isPrimitive() ? castToFieldType(clazz, -1) : null;
        } else if (value instanceof Number) {
            return castToFieldType(clazz, value);
        } else if (value instanceof String) {
            try {
                ParsePosition pp = new ParsePosition(0);
                Number result = numberFormat.parse((String) value, pp);
                if (pp.getIndex() != ((String) value).length()) {
                    throw new ParseException(null, 0);
                }
                return castToFieldType(clazz, result);
            } catch (ParseException e) {
                dataReadingErrors.add(DataValidationResult.builder().valid(false).cell(cell).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.INVALID_NUMBER_FORMAT.getMessage())).valid(false).build());
                return castToFieldType(clazz, -1);
            }
        } else {
            dataReadingErrors.add(DataValidationResult.builder().valid(false).cell(cell).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.INVALID_NUMBER_FORMAT.getMessage())).valid(false).build());
            return castToFieldType(clazz, -1);
        }
    }

    /**
     * Casts the value based on the field type class
     *
     * @param clazz the field type class
     * @param value the value to be casted
     * @param <N>   the field generic type as number
     * @return the casted value based on the field type class
     */
    private <N extends Number> Object castToFieldType(Class<N> clazz, Object value) {
        switch (clazz.getSimpleName().toLowerCase().charAt(0)) {
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
            dateFormat.setLenient(false);
            try {
                return dateFormat.parse((String) value);
            } catch (ParseException e) {
                dataReadingErrors.add(DataValidationResult.builder().valid(false).cell(cell).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.INVALID_DATE_FORMAT.getMessage())).valid(false).build());
                return null;
            }
        } else {
            dataReadingErrors.add(DataValidationResult.builder().valid(false).cell(cell).errorMessages(Collections.singletonList(EExcelItemReaderErrorType.INVALID_DATE_FORMAT.getMessage())).valid(false).build());
            return null;
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
