/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.util.ArrayList;

/**
 * Abstract Excel Cell Validator
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public abstract class AbstractDataValidator {

    protected static final String REQUIRED_FIELD_MSG = "data.validation.field.required";
    protected static final String DEPENDENT_FIELD_MSG = "data.validation.field.dependent";
    protected static final String INVALID_FIELD_TYPE_MSG = "data.validation.field.type.invalid";

    protected boolean mandatory;
    protected Cell dependentField;

    protected AbstractDataValidator(boolean mandatory, Cell dependentField) {
        this.mandatory = mandatory;
        this.dependentField = dependentField;
    }

    /**
     * Validates the content of the given cell
     *
     * @param cell the cell to validate
     * @return the result of the validation
     */
    public DataValidationResult validate(Cell cell) {
        DataValidationResult validationResult = DataValidationResult.builder().cell(cell).errorMessages(new ArrayList<>()).build();
        switch (cell.getCellType()) {
            // in case of empty cell, check id field is mandatory
            case BLANK:
                if (mandatory) {
                    validationResult.setValid(false);
                    validationResult.getErrorMessages().add(REQUIRED_FIELD_MSG);
                } else if (dependentField != null && dependentField.getCellType() == CellType.BLANK) {
                    validationResult.setValid(false);
                    validationResult.getErrorMessages().add(DEPENDENT_FIELD_MSG);
                } else {
                    validationResult.setValid(true);
                }
                break;
            // in case of string cell content, check content against expected format
            case STRING:
                doValidateString(cell, validationResult);
                break;
            // in other cases, add invalid type message
            case BOOLEAN:
                doValidateBoolean(cell, validationResult);
                break;
            case NUMERIC:
                doValidateNumeric(cell, validationResult);
                break;
            case ERROR:
            case FORMULA:
                validationResult.setValid(false);
                validationResult.getErrorMessages().add(INVALID_FIELD_TYPE_MSG);
                break;
        }
        // return validation result
        return validationResult;
    }

    /**
     * Validates numeric content
     *
     * @param cell             the cell to validate
     * @param validationResult the validation result used in the outer validate method
     */
    protected abstract void doValidateNumeric(Cell cell, DataValidationResult validationResult);

    /**
     * Validates boolean content
     *
     * @param cell             the cell to validate
     * @param validationResult the validation result used in the outer validate method
     */
    protected abstract void doValidateBoolean(Cell cell, DataValidationResult validationResult);

    /**
     * Validates string content
     *
     * @param cell             the cell to validate
     * @param validationResult the validation result used in the outer validate method
     */
    protected abstract void doValidateString(Cell cell, DataValidationResult validationResult);


}
