/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.dto.ETransportationType;
import com.elm.shj.admin.portal.services.lookup.TransportationTypeLookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link WithTransportationType} annotation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
public class WithTransportationTypeValidator implements ConstraintValidator<WithTransportationType, Object> {

    @Autowired
    private TransportationTypeLookupService transportationTypeLookupService;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        if (value == null) {
            return false;
        } else {
            return ETransportationType.fromId((Long) value) != null;
        }
    }
}
