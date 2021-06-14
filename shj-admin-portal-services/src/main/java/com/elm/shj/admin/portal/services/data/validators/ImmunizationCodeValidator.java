/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.lookup.HealthImmunizationLookupService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link ImmunizationCode} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class ImmunizationCodeValidator implements ConstraintValidator<ImmunizationCode, Object> {

    @Autowired
    private HealthImmunizationLookupService immunizationLookupService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value != null && immunizationLookupService.existsByCode(value.toString().toUpperCase());
    }

}
