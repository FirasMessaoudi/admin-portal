/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link NullOrNotBlank} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, Object> {

    private int min;
    private int max;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final NullOrNotBlank constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value == null || (NumberUtils.isDigits(value.toString()) && NumberUtils.toInt(value.toString(), -1) >= 0) || (value.toString().length() >= min && value.toString().length() <= max);
    }

}
