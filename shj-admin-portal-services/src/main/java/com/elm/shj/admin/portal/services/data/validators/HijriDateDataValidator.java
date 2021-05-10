/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Hijri Date Excel Cell Validator
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class HijriDateDataValidator extends AbstractDataValidator {

    private static final String INVALID_HIJRI_FORMAT_MSG = "data.validation.date.hijri.format";
    private static final String HIJRI_DATE_OUT_OF_RANGE_MSG = "data.validation.date.hijri.out.of.range";
    private static final int DEFAULT_MIN_DATE = 13000101;
    private static final int DEFAULT_MAX_DATE = 15000101;

    private final int minDate;
    private final int maxDate;

    public HijriDateDataValidator(boolean mandatory) {
        super(mandatory);
        this.minDate = DEFAULT_MIN_DATE;
        this.maxDate = DEFAULT_MAX_DATE;
    }

    public HijriDateDataValidator(boolean mandatory, int minDate, int maxDate) {
        super(mandatory);
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doValidateBoolean(Cell cell, DataValidationResult validationResult) {
        validationResult.setValid(false);
        validationResult.getErrorMessages().add(INVALID_HIJRI_FORMAT_MSG);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doValidateString(Cell cell, DataValidationResult validationResult) {
        if (StringUtils.isNumeric(cell.getStringCellValue())) {
            int hijriDate = Integer.parseInt(cell.getStringCellValue());
            validateValue(validationResult, hijriDate);
        } else {
            validationResult.setValid(false);
            validationResult.getErrorMessages().add(INVALID_HIJRI_FORMAT_MSG);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doValidateNumeric(Cell cell, DataValidationResult validationResult) {
        int hijriDate = (int) cell.getNumericCellValue();
        validateValue(validationResult, hijriDate);
    }

    /**
     * Validates hijri date integer and updates the validation result
     *
     * @param validationResult the validation result used in the outer validate method
     * @param hijriDate        the hijri date to validate
     */
    private void validateValue(DataValidationResult validationResult, int hijriDate) {
        if (!isValidHijriFormat(hijriDate)) {
            validationResult.setValid(false);
            validationResult.getErrorMessages().add(INVALID_HIJRI_FORMAT_MSG);
            return;
        } else if (!isBetween(hijriDate, minDate, maxDate)) {
            validationResult.setValid(false);
            validationResult.getErrorMessages().add(HIJRI_DATE_OUT_OF_RANGE_MSG);
            return;
        }
        validationResult.setValid(true);
    }

    /**
     * Validates hijri date integer
     *
     * @param hijriDate the hijri date to validate
     * @return whether the passed hijri date is valid or not
     */
    private boolean isValidHijriFormat(int hijriDate) {
        String hijriDateString = Integer.toString(hijriDate);
        if (hijriDateString.length() == 8) {
            int year = Integer.parseInt(hijriDateString.substring(0, 4));
            int month = Integer.parseInt(hijriDateString.substring(4, 6));
            int day = Integer.parseInt(hijriDateString.substring(6, 8));
            return year > 1000
                    && isBetween(month, 1, 12)
                    && isBetween(day, 1, 30);
        }
        return false;
    }

    /**
     * Checks if the variable is within boundaries
     *
     * @param variable          the variable to check
     * @param minValueInclusive min boundary
     * @param maxValueInclusive max boundary
     * @return if the variable is within boundaries
     */
    private boolean isBetween(int variable, int minValueInclusive, int maxValueInclusive) {
        return variable >= minValueInclusive && variable <= maxValueInclusive;
    }
}
