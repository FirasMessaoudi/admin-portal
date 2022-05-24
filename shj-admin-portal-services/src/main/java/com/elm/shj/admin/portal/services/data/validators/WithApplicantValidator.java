/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicInfoDto;
import com.elm.shj.admin.portal.services.dto.StaffApplicantGroupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Calendar;

/**
 * Validator for {@link WithApplicant} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
public class WithApplicantValidator implements ConstraintValidator<WithApplicant, Object> {

    @Autowired
    private ApplicantService applicantService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null) {
            return false;

        }
        if (value instanceof StaffApplicantGroupDto) {
            StaffApplicantGroupDto staffApplicantGroupDto =(StaffApplicantGroupDto) value;
            ApplicantBasicInfoDto applicantBasicInfoDto = new ApplicantBasicInfoDto();
            applicantBasicInfoDto.setIdNumber(staffApplicantGroupDto.getIdNumber());
            applicantBasicInfoDto.setPassportNumber(staffApplicantGroupDto.getPassportNumber());
            applicantBasicInfoDto.setDateOfBirthGregorian(staffApplicantGroupDto.getDateOfBirthGregorian());
            applicantBasicInfoDto.setDateOfBirthHijri(staffApplicantGroupDto.getDateOfBirthHijri());
            return applicantService.existsByBasicInfo(applicantBasicInfoDto);
        } else {
            Field applicantBasicInfoField = ReflectionUtils.findField(value.getClass(), "applicantBasicInfo");
            if (applicantBasicInfoField == null) {
                return false;
            }
            try {
                // make fields accessible
                ReflectionUtils.makeAccessible(applicantBasicInfoField);
                // get applicant basic info from the current object
                ApplicantBasicInfoDto applicantBasicInfo = (ApplicantBasicInfoDto) applicantBasicInfoField.get(value);
                if (applicantBasicInfo.getDateOfBirthGregorian() != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(applicantBasicInfo.getDateOfBirthGregorian());
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    applicantBasicInfo.setDateOfBirthGregorian(calendar.getTime());
                }
                // search applicant by his basic info from the database
                return applicantService.existsByBasicInfo(applicantBasicInfo);
            } catch (IllegalAccessException e) {
                ReflectionUtils.handleReflectionException(e);
            }
            return false;
        }


    }
}
