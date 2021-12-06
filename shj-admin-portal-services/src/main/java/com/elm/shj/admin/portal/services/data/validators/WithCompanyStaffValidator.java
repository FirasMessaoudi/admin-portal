/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.CompanyStaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * Validator for {@link WithCompanyStaff} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
public class WithCompanyStaffValidator implements ConstraintValidator<WithCompanyStaff, Object> {

    @Autowired
    private CompanyStaffService companyStaffService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        Field idNumberField = ReflectionUtils.findField(value.getClass(), "idNumber");
        Field passportNumberField = ReflectionUtils.findField(value.getClass(), "passportNumber");
        Field dateOfBirthGregorianField = ReflectionUtils.findField(value.getClass(), "dateOfBirthGregorian");
        Field dateOfBirthHijriField = ReflectionUtils.findField(value.getClass(), "dateOfBirthHijri");

        if (value == null || (idNumberField == null && passportNumberField == null) || (dateOfBirthGregorianField == null && dateOfBirthHijriField == null)) {
            return false;
        }
        try {
            // make fields accessible
            ReflectionUtils.makeAccessible(idNumberField);
            ReflectionUtils.makeAccessible(passportNumberField);
            ReflectionUtils.makeAccessible(dateOfBirthGregorianField);
            ReflectionUtils.makeAccessible(dateOfBirthHijriField);
            String idNumber = idNumberField.get(value) != null ? idNumberField.get(value).toString() : null;
            String passportNumber = passportNumberField.get(value) != null ? passportNumberField.get(value).toString() : null;
            Date dateOfBirthGregorian = dateOfBirthGregorianField.get(value) != null ? (Date) dateOfBirthGregorianField.get(value) : null;
            Long dateOfBirthHijri = dateOfBirthHijriField.get(value) != null ? (Long) dateOfBirthHijriField.get(value) : null;
            return companyStaffService.existsByBasicInfo(idNumber, passportNumber, dateOfBirthGregorian, dateOfBirthHijri);
        } catch (IllegalAccessException e) {
            ReflectionUtils.handleReflectionException(e);
        }
        return false;
    }

}
