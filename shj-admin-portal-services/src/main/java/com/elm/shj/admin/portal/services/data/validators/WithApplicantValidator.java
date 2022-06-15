/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.ApplicantLiteService;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.data.huic.HuicApplicantRelative;
import com.elm.shj.admin.portal.services.data.huic.HuicApplicantRitual;
import com.elm.shj.admin.portal.services.data.huic.HuicArrivalData;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicInfoDto;
import com.elm.shj.admin.portal.services.dto.GroupDataDto;
import com.elm.shj.admin.portal.services.dto.StaffApplicantGroupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

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

    @Autowired
    private ApplicantLiteService applicantLiteService;

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
            if (value.getClass().isAssignableFrom(GroupDataDto.class)) {
                if (((GroupDataDto) value).getNationalityCode() == null) {
                    return false;
                }
                return applicantLiteService.existsByBasicInfo(((GroupDataDto) value).getIdNumber(), ((GroupDataDto) value).getPassportNumber(), ((GroupDataDto) value).getNationalityCode());
            }
            if (value.getClass().isAssignableFrom(HuicApplicantRitual.class)) {
                if (((HuicApplicantRitual) value).getNationality() == null) {
                    return false;
                }
                return applicantLiteService.existsByBasicInfo(((HuicApplicantRitual) value).getIdNumber() != null ? ((HuicApplicantRitual) value).getIdNumber().toString() : null, ((HuicApplicantRitual) value).getPassportNo(), ((HuicApplicantRitual) value).getNationality().toString());
            }
            if (value.getClass().isAssignableFrom(HuicApplicantRelative.class)) {
                if (((HuicApplicantRelative) value).getNationality() == null) {
                    return false;
                }
                return applicantLiteService.existsByBasicInfo(((HuicApplicantRelative) value).getIdNumber() != null ? ((HuicApplicantRelative) value).getIdNumber().toString() : null, ((HuicApplicantRelative) value).getPassportNo(), ((HuicApplicantRelative) value).getNationality().toString());
            }
            if (value.getClass().isAssignableFrom(HuicArrivalData.class)) {
                if (value.getClass().isAssignableFrom(HuicArrivalData.class)) {
                    if (((HuicArrivalData) value).getNationality() == null) {
                        return false;
                    }
                    return applicantLiteService.existsByBasicInfo(((HuicArrivalData) value).getIdNumber() != null ? ((HuicArrivalData) value).getIdNumber().toString() : null, ((HuicArrivalData) value).getPassportNo(), ((HuicArrivalData) value).getNationality().toString());
                }
            }
            Field applicantBasicInfoField = ReflectionUtils.findField(value.getClass(), "applicantBasicInfo");
            if (applicantBasicInfoField == null) {
                return false;
            }
            try {
                // make fields accessible
                ReflectionUtils.makeAccessible(applicantBasicInfoField);
                // get applicant basic info from the current object
                ApplicantBasicInfoDto applicantBasicInfo = (ApplicantBasicInfoDto) applicantBasicInfoField.get(value);
                // search applicant by his basic info from the database
                return applicantService.existsByBasicInfo(applicantBasicInfo);
            } catch (IllegalAccessException e) {
                ReflectionUtils.handleReflectionException(e);
            }
            return false;
        }


    }
}
