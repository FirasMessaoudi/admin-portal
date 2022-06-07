/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.dto.ECompanyType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link CompanyTypeCode} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public class CompanyTypeCodeValidator implements ConstraintValidator<CompanyTypeCode, Object> {


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value != null && ECompanyType.fromId((Integer) value) != null;
    }

}
