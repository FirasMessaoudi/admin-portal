/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.services.data.segment.DataSegmentService;
import com.elm.shj.admin.portal.services.dto.DataRequestDto;
import com.elm.shj.admin.portal.services.dto.DataSegmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for {@link DataSegmentFormat} annotation
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class DataSegmentFormatValidator implements ConstraintValidator<DataSegmentFormat, Object> {

    @Autowired
    private DataSegmentService dataSegmentService;

    @Autowired
    private MultipartHttpServletRequest request;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (!(value instanceof DataRequestDto) || ((DataRequestDto) value).getDataSegment() == null) {
            return false;
        }
        DataRequestDto dataRequest = (DataRequestDto) value;
        DataSegmentDto dataSegment = dataSegmentService.findOne(dataRequest.getDataSegment().getId());

        MultipartFile file = new StandardMultipartHttpServletRequest(request).getFile("file");
        if (file == null) {
            return false;
        }
        // continue
        return true;
    }

}
