/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.ApplicantLiteService;
import com.elm.shj.admin.portal.services.data.huic.HuicApplicantRelative;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicInfoDto;
import com.elm.shj.admin.portal.services.dto.ApplicantRelativeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link WithRelative} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
public class WithRelativeValidator implements ConstraintValidator<WithRelative, Object> {

    @Autowired
    private ApplicantLiteService applicantService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null || (!value.getClass().isAssignableFrom(ApplicantRelativeDto.class)) && !value.getClass().isAssignableFrom(HuicApplicantRelative.class)) {
            return false;
        }
        // search relative applicant by his basic info from the database
        if (value.getClass().isAssignableFrom(ApplicantRelativeDto.class))
            return applicantService.existsByBasicInfo(ApplicantBasicInfoDto.fromRelative((ApplicantRelativeDto) value));
        else {
            if (((HuicApplicantRelative) value).getRelativeNationality() == null) {
                return false;
            }
            return applicantService.existsByBasicInfo(((HuicApplicantRelative) value).getRelativeIdNumber() != null ? ((HuicApplicantRelative) value).getRelativeIdNumber().toString() : null, ((HuicApplicantRelative) value).getRelativePassportNo(), ((HuicApplicantRelative) value).getRelativeNationality().toString());
        }
    }

}
