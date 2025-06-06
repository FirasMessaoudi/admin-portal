/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link BorderNumber} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class BorderNumberValidator implements ConstraintValidator<BorderNumber, Object> {

    private static final String BORDER_NUMBER_REGEX = "^[3]\\d{9}$";
    private static final String MSG_20012 = "validation.data.constraints.msg.20012";

    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        // allow null/empty
        if (value == null || StringUtils.isBlank(value.toString())) {
            return true;
        } else if (value.toString().length() != 10) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MSG_20012).addConstraintViolation();
            return false;
        } else if (!value.toString().matches(BORDER_NUMBER_REGEX)) {
            return false;
        }
        return true;
    }

}
