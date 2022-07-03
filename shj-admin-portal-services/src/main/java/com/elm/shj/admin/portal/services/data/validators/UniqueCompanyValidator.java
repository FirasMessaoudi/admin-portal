/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.company.CompanyService;
import com.elm.shj.admin.portal.services.data.huic.HuicCompany;
import com.elm.shj.admin.portal.services.dto.ECompanyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link UniqueCompany} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
public class UniqueCompanyValidator implements ConstraintValidator<UniqueCompany, Object> {

    @Autowired
    private CompanyService companyService;


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null || !value.getClass().isAssignableFrom(HuicCompany.class)) {
            return false;
        }
        HuicCompany huicCompany = (HuicCompany) value;
        return ECompanyType.fromId(huicCompany.getCompanyTypeCode()) != null && !companyService.existsByBasicInfo(huicCompany.getCompanyRefCode() + "", huicCompany.getCompanyTypeCode());
    }

}
