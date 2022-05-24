/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link IdNumber} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class IdNumberValidator implements ConstraintValidator<IdNumber, Object> {

    private static final String MSG_20003 = "validation.data.constraints.msg.20003";
    private static final String MSG_20004 = "validation.data.constraints.msg.20004";
    private static final String NUMBERS_ONLY_REGEX = "^[0-9_-]*$";
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
        if (value == null || StringUtils.isBlank(value.toString())) {
            return true;
        } else if (!value.toString().matches(NUMBERS_ONLY_REGEX)) {
            // build new violation message and add it
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MSG_20003).addConstraintViolation();
            return false;
        } else // return default message
            // (length 11 to 16 => GCC)
            if (value.toString().length() < minLength || value.toString().length() > maxLength) {
                // build new violation message and add it
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(MSG_20004).addConstraintViolation();
                return false;
            }
            // ( length =10 and start with (1 or 2))
            else return !ninOrIqama || value.toString().length() != 10 || value.toString().matches(NIN_OR_IQAMA_REGEX);
    }

}
