/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.ApplicantService;
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
    private ApplicantService applicantService;

    @Value("${data.request.applicant.override}")
    private boolean overrideApplicantData;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null || !value.getClass().isAssignableFrom(ApplicantDto.class)) {
            return false;
        }
        // applicant should not exist or override flag should be true
        return overrideApplicantData || !applicantService.existsByBasicInfoAndPackageCode(ApplicantBasicInfoDto.fromApplicant((ApplicantDto) value), ((ApplicantDto) value).getPackageReferenceNumber());
    }

}
