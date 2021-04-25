/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validation annotation to validate that file uploaded is matching the expected data segment format.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DataSegmentFormatValidator.class)
@Documented
public @interface DataSegmentFormat {

    String message() default "{validation.constraints.data-segment-format}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
