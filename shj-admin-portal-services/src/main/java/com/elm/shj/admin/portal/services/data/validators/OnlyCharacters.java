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
 * Validation annotation to validate that a value has only characters
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = OnlyCharactersValidator.class)
@Documented
public @interface OnlyCharacters {

    String message() default "validation.data.constraints.msg.018";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return if characters should only be arabic
     */
    boolean arabic() default false;

    /**
     * @return if to allow empty values
     */
    boolean allowEmpty() default true;

    /**
     * @return The min field length
     */
    int min();

    /**
     * @return The max field length
     */
    int max();

}
