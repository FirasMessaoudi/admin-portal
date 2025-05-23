/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.lookup.CountryLookupService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link CountryCode} annotation
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
public class CountryCodeValidator implements ConstraintValidator<CountryCode, Object> {

    @Autowired
    private CountryLookupService countryLookupService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value == null || countryLookupService.existsByCode(value.toString());
    }

}
