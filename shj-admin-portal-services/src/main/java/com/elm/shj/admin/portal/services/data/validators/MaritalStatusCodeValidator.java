/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.dto.EMaritalStatus;
import com.elm.shj.admin.portal.services.lookup.MaritalStatusLookupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link MaritalStatusCode} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class MaritalStatusCodeValidator implements ConstraintValidator<MaritalStatusCode, Object> {

    @Autowired
    private MaritalStatusLookupService maritalStatusLookupService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null || value.getClass().isAssignableFrom(String.class)) {
            return value == null || StringUtils.isBlank(value.toString()) || maritalStatusLookupService.existsByCode(value.toString().toUpperCase());
        } else {
            return EMaritalStatus.fromId((Long) value) != null;
        }
    }

}
