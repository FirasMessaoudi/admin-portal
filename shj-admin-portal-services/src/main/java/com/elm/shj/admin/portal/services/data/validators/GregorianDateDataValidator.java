/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Gregorian Date Excel Cell Validator
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class GregorianDateDataValidator extends AbstractDataValidator {

    private static final String INVALID_GREGORIAN_FORMAT_MSG = "data.validation.date.gregorian.format";
    private static final String GREGORIAN_DATE_OUT_OF_RANGE_MSG = "data.validation.date.gregorian.out.of.range";
    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    private static final Date DEFAULT_MIN_DATE = new GregorianCalendar(1900, Calendar.JANUARY, 1).getTime();
    private static final Date DEFAULT_MAX_DATE = new GregorianCalendar(2050, Calendar.JANUARY, 1).getTime();

    private final SimpleDateFormat dateFormatter;
    private final Date minDate;
    private final Date maxDate;

    public GregorianDateDataValidator(boolean mandatory) {
        super(mandatory);
        this.dateFormatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        this.minDate = DEFAULT_MIN_DATE;
        this.maxDate = DEFAULT_MAX_DATE;
    }

    public GregorianDateDataValidator(String dateFormat, boolean mandatory) {
        super(mandatory);
        this.dateFormatter = new SimpleDateFormat(dateFormat);
        this.minDate = DEFAULT_MIN_DATE;
        this.maxDate = DEFAULT_MAX_DATE;
    }

    public GregorianDateDataValidator(String dateFormat, boolean mandatory, Date minDate, Date maxDate) {
        super(mandatory);
        this.dateFormatter = new SimpleDateFormat(dateFormat);
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doValidateNumeric(Cell cell, DataValidationResult validationResult) {
        boolean cellDateFormatted = DateUtil.isCellDateFormatted(cell);
        if (!cellDateFormatted) {
            validationResult.setValid(false);
            validationResult.getErrorMessages().add(INVALID_GREGORIAN_FORMAT_MSG);
        }
        if (isBetween(cell.getDateCellValue(), minDate, maxDate)) {
            validationResult.setValid(false);
            validationResult.getErrorMessages().add(GREGORIAN_DATE_OUT_OF_RANGE_MSG);
        }
        validationResult.setValid(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doValidateBoolean(Cell cell, DataValidationResult validationResult) {
        validationResult.setValid(false);
        validationResult.getErrorMessages().add(INVALID_GREGORIAN_FORMAT_MSG);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doValidateString(Cell cell, DataValidationResult validationResult) {
        try {
            Date parsedValue = dateFormatter.parse(cell.getStringCellValue());
            if (!isBetween(parsedValue, minDate, maxDate)) {
                validationResult.setValid(false);
                validationResult.getErrorMessages().add(GREGORIAN_DATE_OUT_OF_RANGE_MSG);
            } else {
                validationResult.setValid(true);
            }
        } catch (ParseException e) {
            validationResult.setValid(false);
            validationResult.getErrorMessages().add(INVALID_GREGORIAN_FORMAT_MSG);
        }
    }

    /**
     * Checks if the variable is within boundaries
     *
     * @param variable          the variable to check
     * @param minValueInclusive min boundary
     * @param maxValueInclusive max boundary
     * @return if the variable is within boundaries
     */
    private boolean isBetween(Date variable, Date minValueInclusive, Date maxValueInclusive) {
        return variable != null && variable.after(minValueInclusive) && variable.before(maxValueInclusive);
    }
}
