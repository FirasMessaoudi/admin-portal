/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicInfoDto;
import com.elm.shj.admin.portal.services.dto.ApplicantEmergencyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link UniqueApplicantEmergency} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
public class UniqueApplicantEmergencyValidator implements ConstraintValidator<UniqueApplicantEmergency, Object> {

    @Autowired
    private ApplicantService applicantService;

    @Value("${data.request.applicant.override}")
    private boolean overrideApplicantData;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null || !value.getClass().isAssignableFrom(ApplicantEmergencyDto.class)) {
            return false;
        }
        // applicant should not exist or override flag should be true
        return overrideApplicantData || !applicantService.existsByBasicInfoAndPackageCode(ApplicantBasicInfoDto.fromApplicantEmergency((ApplicantEmergencyDto) value), ((ApplicantEmergencyDto) value).getPackageReferenceNumber());
    }

}
