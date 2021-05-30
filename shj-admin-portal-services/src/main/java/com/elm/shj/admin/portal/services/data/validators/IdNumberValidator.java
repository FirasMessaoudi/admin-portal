/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link IdNumber} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class IdNumberValidator implements ConstraintValidator<IdNumber, Object> {

    private static final String MSG_121 = "validation.data.constraints.msg.121";
    private static final String MSG_122 = "validation.data.constraints.msg.122";
    private static final String NUMBERS_ONLY_REGEX = "^[\\p{N}\\s]+$";
    private static final String NIN_OR_IQAMA_REGEX = "[1-2]\\d{9}";

    private int minLength;
    private int maxLength;
    private boolean ninOrIqama;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final IdNumber constraintAnnotation) {
        minLength = constraintAnnotation.minLength();
        maxLength = constraintAnnotation.maxLength();
        ninOrIqama = constraintAnnotation.ninOrIqama();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        // allow null
        if (value == null) {
            return true;
        } else if (!value.toString().matches(NUMBERS_ONLY_REGEX)) {
            // build new violation message and add it
            context.buildConstraintViolationWithTemplate(MSG_121).addConstraintViolation();
            return false;
        } else if (value.toString().length() < minLength || value.toString().length() > maxLength) {
            // build new violation message and add it
            context.buildConstraintViolationWithTemplate(MSG_122).addConstraintViolation();
            return false;
        }
        // (length =16 and GCC) or ( length =10 and start with (1 or 2))
        else if (ninOrIqama && !value.toString().matches(NIN_OR_IQAMA_REGEX)) {
            // return default message
            return false;
        } else {
            return true;
        }
    }

}
