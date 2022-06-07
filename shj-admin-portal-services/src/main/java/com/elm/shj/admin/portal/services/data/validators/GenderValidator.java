/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * Validator for {@link Gender} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class GenderValidator implements ConstraintValidator<Gender, Object> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        // M or F
        if (value.getClass().isAssignableFrom(String.class)) {
            return "M".equalsIgnoreCase(Objects.toString(value)) || "F".equalsIgnoreCase(Objects.toString(value));
        } else {
            return value != null && ((value).equals(1) || ((value).equals(2)));
        }
    }

}
