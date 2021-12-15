/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonService;
import com.elm.shj.admin.portal.services.dto.CompanyRitualSeasonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * Validator for {@link WithCompanyStaff} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
public class WithCompanyRitualSeasonValidator implements ConstraintValidator<WithCompanyRitualSeason, Object> {

    @Autowired
    private CompanyRitualSeasonService companyRitualSeasonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        Field typeCodeField = ReflectionUtils.findField(value.getClass(), "typeCode");
        Field seasonField = ReflectionUtils.findField(value.getClass(), "season");
        Field companyCodeField = ReflectionUtils.findField(value.getClass(), "companyCode");

        if (value == null) {
            return false;
        }
        try {
            // make fields accessible
            ReflectionUtils.makeAccessible(typeCodeField);
            ReflectionUtils.makeAccessible(seasonField);
            ReflectionUtils.makeAccessible(companyCodeField);
            CompanyRitualSeasonDto companyRitualSeason = companyRitualSeasonService.getLatestCompanyRitualSeasonByRitualSeason((String) companyCodeField.get(value), (String) typeCodeField.get(value), (int) seasonField.get(value));
            return companyRitualSeason != null;
        } catch (IllegalAccessException e) {
            ReflectionUtils.handleReflectionException(e);
        }
        return false;
    }

}
