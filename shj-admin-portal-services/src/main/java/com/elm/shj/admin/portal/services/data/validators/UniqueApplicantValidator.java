/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.ApplicantLiteService;
import com.elm.shj.admin.portal.services.data.huic.HuicApplicantMainData;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicInfoDto;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link UniqueApplicant} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
public class UniqueApplicantValidator implements ConstraintValidator<UniqueApplicant, Object> {

    @Autowired
    private ApplicantLiteService applicantLiteService;

    @Value("${data.request.applicant.override}")
    private boolean overrideApplicantData;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null || (!value.getClass().isAssignableFrom(ApplicantDto.class)) && !value.getClass().isAssignableFrom(HuicApplicantMainData.class)) {
            return false;
        }
        // applicant should not exist or override flag should be true
        if (value.getClass().isAssignableFrom(ApplicantDto.class)) {
            return overrideApplicantData || !applicantLiteService.existsByBasicInfo(ApplicantBasicInfoDto.fromApplicant((ApplicantDto) value));
        } else {
            HuicApplicantMainData huicApplicantMainData = (HuicApplicantMainData) value;
            if (huicApplicantMainData.getStatus() == null || huicApplicantMainData.getStatus() == 1) {
                String idNumber = ((HuicApplicantMainData) value).getIdNumber() != null ? ((HuicApplicantMainData) value).getIdNumber().toString() : null;
                String nationalityCode = ((HuicApplicantMainData) value).getNationality() != null ? ((HuicApplicantMainData) value).getNationality().toString() : null;
                return !applicantLiteService.existsByBasicInfo(idNumber, ((HuicApplicantMainData) value).getPassportNo(), nationalityCode);
            } else {
                return true;
            }
        }
    }
}
