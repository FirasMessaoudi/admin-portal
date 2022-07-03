/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.dto.ERitualType;
import com.elm.shj.admin.portal.services.lookup.RitualTypeLookupService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link RitualTypeCode} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class RitualTypeCodeValidator implements ConstraintValidator<RitualTypeCode, Object> {

    @Autowired
    private RitualTypeLookupService ritualTypeLookupService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value != null && value.getClass().isAssignableFrom(String.class)) {
            return ritualTypeLookupService.existsByCode(value.toString().toUpperCase());
        } else {
            return value != null && ERitualType.fromId((Integer) value) != null;
        }
    }

}
