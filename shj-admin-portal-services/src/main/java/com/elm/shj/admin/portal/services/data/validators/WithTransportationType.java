/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validation annotation to validate that transportation type corresponds to an existing transportation type in the database
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = WithTransportationTypeValidator.class)
@Documented
public @interface WithTransportationType {

    String message() default "validation.data.constraints.msg.30018";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
