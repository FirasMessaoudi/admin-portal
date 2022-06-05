/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.lookup.CompanyStaffTitleLookupService;
import com.elm.shj.admin.portal.services.lookup.MaritalStatusLookupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link JobTitleCode} annotation
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public class JobTitleCodeValidator implements ConstraintValidator<JobTitleCode, Object> {


    @Autowired
    private CompanyStaffTitleLookupService  companyStaffTitleLookupService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if(!value.toString().toUpperCase().equals("OTHERS"))
            return  value != null && companyStaffTitleLookupService.existsByCode(value.toString().toUpperCase());
        else
            return true;
    }

}
