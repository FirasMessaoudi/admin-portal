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
 * Validation annotation to validate that file uploaded is matching the expected data segment structure.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DataSegmentStructureValidator.class)
@Documented
public @interface DataSegmentStructure {

    String message() default "validation.constraints.data-segment-structure";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
