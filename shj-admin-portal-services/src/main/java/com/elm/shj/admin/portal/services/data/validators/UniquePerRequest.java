/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validation annotation to validate that a field should be unique per request.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Documented
public @interface UniquePerRequest {

    String message() default "validation.data.constraints.msg.20010";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
