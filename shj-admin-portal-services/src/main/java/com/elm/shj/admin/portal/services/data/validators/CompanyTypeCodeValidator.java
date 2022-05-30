/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * Validator for {@link CompanyTypeCode} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public class CompanyTypeCodeValidator implements ConstraintValidator<CompanyTypeCode, Object> {

    private final static List<String> COMPANY_TYPES = Arrays.asList("1", "2", "3", "4");

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value != null && COMPANY_TYPES.stream().anyMatch(value.toString()::equalsIgnoreCase);
    }

}
