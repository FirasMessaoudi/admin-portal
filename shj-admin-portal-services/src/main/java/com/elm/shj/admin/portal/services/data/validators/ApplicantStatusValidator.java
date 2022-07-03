/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * Validator for {@link ApplicantStatus} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public class ApplicantStatusValidator implements ConstraintValidator<ApplicantStatus, Object> {
    private final static List<String> STATUS = Arrays.asList("1", "2", "3");


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value == null || STATUS.stream().anyMatch(value.toString()::equals);
    }

}
