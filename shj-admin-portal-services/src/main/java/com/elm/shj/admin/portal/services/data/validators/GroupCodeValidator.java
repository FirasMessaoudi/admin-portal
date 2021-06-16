/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.group.RitualGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link GroupCode} annotation
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
public class GroupCodeValidator implements ConstraintValidator<GroupCode, Object> {

    @Autowired
    private RitualGroupService groupService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value != null && groupService.existsByCode(value.toString().toUpperCase());
    }
}
