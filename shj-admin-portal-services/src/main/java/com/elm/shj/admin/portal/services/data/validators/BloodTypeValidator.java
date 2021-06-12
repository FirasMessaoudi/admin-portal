/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * Validator for {@link BloodType} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class BloodTypeValidator implements ConstraintValidator<BloodType, Object> {

    private final static List<String> BLOOD_TYPES = Arrays.asList("O−", "O+", "A−", "A+", "B−", "B+", "AB−", "AB+");

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value == null || BLOOD_TYPES.stream().anyMatch(value.toString()::equalsIgnoreCase);
    }

}
