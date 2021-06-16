/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.unit.RitualUnitService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link UnitCode} annotation
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public class UnitCodeValidator implements ConstraintValidator<UnitCode, Object> {

    @Autowired
    private RitualUnitService unitService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value != null && unitService.existsByCode(value.toString().toUpperCase());
    }
}
