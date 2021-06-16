/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.zone.RitualZoneService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link ZoneCode} annotation
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public class ZoneCodeValidator implements ConstraintValidator<ZoneCode, Object> {

    @Autowired
    private RitualZoneService zoneService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value != null && zoneService.existsByCode(value.toString().toUpperCase());
    }
}
