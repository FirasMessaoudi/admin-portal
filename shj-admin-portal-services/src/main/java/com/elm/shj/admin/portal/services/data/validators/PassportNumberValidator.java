/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link PassportNumber} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class PassportNumberValidator implements ConstraintValidator<PassportNumber, Object> {

    private static final String MSG_20003 = "validation.data.constraints.msg.20003";
    private static final String LETTERS_AND_NUMBERS_REGEX = "^[\\p{IsLatin}\\s\\p{N}]+$";

    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        // allow null
        if (value == null) {
            return true;
        } else // return default message
            if (!value.toString().matches(LETTERS_AND_NUMBERS_REGEX)) {
            // build new violation message and add it
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(MSG_20003).addConstraintViolation();
            return false;
        }
        // Allowed chars only number and english && length between ( 5 , 30)
        else return value.toString().length() >= 5 && value.toString().length() <= 30;
    }

}
