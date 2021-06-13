/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link VisaOrPermitNumber} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class VisaOrPermitNumberValidator implements ConstraintValidator<VisaOrPermitNumber, Object> {

    private static final String MSG_20003 = "validation.data.constraints.msg.20003";
    private static final String LETTERS_AND_NUMBERS_REGEX = "^[\\p{N}]+$";

    private boolean allowEmpty;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final VisaOrPermitNumber constraintAnnotation) {
        allowEmpty = constraintAnnotation.allowEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        // allow null or not
        if (value == null || StringUtils.isBlank(value.toString())) {
            return allowEmpty;
        } else // return default message
            if (!value.toString().matches(LETTERS_AND_NUMBERS_REGEX)) {
                // build new violation message and add it
                context.buildConstraintViolationWithTemplate(MSG_20003).addConstraintViolation();
                return false;
            }
            // Allowed chars only number and english && length between ( 5 , 30)
            else return value.toString().length() >= 5 && value.toString().length() <= 50;
    }

}
