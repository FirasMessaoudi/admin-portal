/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.applicant.HousingMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link WithHousingMaster} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
public class WithHousingMasterValidator implements ConstraintValidator<WithHousingMaster, Object> {

    @Autowired
    private HousingMasterService housingMasterService;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        return value != null && housingMasterService.existsByHousingReferenceCode(value.toString());

    }
}
