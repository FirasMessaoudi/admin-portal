/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.CompanyLiteService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link CompanyCode} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public class CompanyCodeValidator implements ConstraintValidator<CompanyCode, Object> {

    @Autowired
    private CompanyLiteService companyLiteService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value != null && companyLiteService.existsByCode(value.toString().toUpperCase());
    }

}
