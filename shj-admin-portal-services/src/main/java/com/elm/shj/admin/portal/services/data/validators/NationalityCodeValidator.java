/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.lookup.NationalityLookupService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link NationalityCode} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class NationalityCodeValidator implements ConstraintValidator<NationalityCode, Object> {

    @Autowired
    private NationalityLookupService nationalityLookupService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value == null || nationalityLookupService.existsByCode(value.toString().toUpperCase());
    }

}
