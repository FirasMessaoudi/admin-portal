/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.lookup.RelativeRelationshipLookupService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link RelationshipCode} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class RelationshipCodeValidator implements ConstraintValidator<RelationshipCode, Object> {

    @Autowired
    private RelativeRelationshipLookupService relationshipLookupService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return value == null || relationshipLookupService.existsByCode(value.toString().toUpperCase());
    }

}
