/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.company.CompanyStaffService;
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
        Field idNumber = ReflectionUtils.findField(value.getClass(), "idNumber");
        Field passportNumber = ReflectionUtils.findField(value.getClass(), "passportNumber");
        Field dateOfBirthGregorian = ReflectionUtils.findField(value.getClass(), "dateOfBirthGregorian");
        Field dateOfBirthHijri = ReflectionUtils.findField(value.getClass(), "dateOfBirthHijri");

        if (value == null || idNumber == null || passportNumber == null || dateOfBirthGregorian == null || dateOfBirthHijri == null) {
            return false;
        }
        try {
            // make fields accessible
            ReflectionUtils.makeAccessible(idNumber);
            ReflectionUtils.makeAccessible(passportNumber);
            ReflectionUtils.makeAccessible(dateOfBirthGregorian);
            ReflectionUtils.makeAccessible(dateOfBirthHijri);

            return companyStaffService.existsByBasicInfo(idNumber.get(value).toString(), (String) passportNumber.get(value), (Date) dateOfBirthGregorian.get(value), (Long) dateOfBirthHijri.get(value));
        } catch (IllegalAccessException e) {
            ReflectionUtils.handleReflectionException(e);
        }
        return false;
    }

}
