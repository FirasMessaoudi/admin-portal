/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.dto.EArrivalCity;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link WithArrivalCity} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
public class WithArrivalCityValidator implements ConstraintValidator<WithArrivalCity, Object> {


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        return EArrivalCity.fromId((Integer) value) != null;

    }
}
