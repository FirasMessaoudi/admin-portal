/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link ContentType} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class ContentTypeValidator implements ConstraintValidator<ContentType, Object> {

    private String contentType;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final ContentType constraintAnnotation) {
        contentType = constraintAnnotation.contentType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return (value instanceof MultipartFile) && contentType.equals(((MultipartFile) value).getContentType());
    }
}
