/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.ApplicantGroupService;
import com.elm.shj.admin.portal.services.applicant.RitualPackageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link WithGroupReferenceNumber} annotation
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Slf4j
public class WithGroupReferenceNumberValidator implements ConstraintValidator<WithGroupReferenceNumber, Object> {

    @Autowired
    private ApplicantGroupService  applicantGroupService;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        if (value == null || StringUtils.isBlank(value.toString())) {
            return false;
        } else {
            return applicantGroupService.getApplicantGroupByReferenceNumber(value.toString())!=null;
        }
    }
}
