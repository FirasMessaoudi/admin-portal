/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * Validator for {@link Mandatory} annotation
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
public class MandatoryValidator implements ConstraintValidator<Mandatory, Object> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        // true or false
        return "true".equalsIgnoreCase(Objects.toString(value)) || "false".equalsIgnoreCase(Objects.toString(value));
    }
}
