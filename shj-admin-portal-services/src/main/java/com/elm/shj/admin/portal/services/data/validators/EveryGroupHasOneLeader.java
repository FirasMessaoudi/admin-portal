/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validation annotation to validate that .every applicant group should have only one leader
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Documented
public @interface EveryGroupHasOneLeader {

    String message() default "validation.data.constraints.msg.30005";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
