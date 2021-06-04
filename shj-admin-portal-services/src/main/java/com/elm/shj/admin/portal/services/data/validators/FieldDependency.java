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
 * Validation annotation to validate that 2 fields have value at least on of them. An array of fields and their dependent fields can be supplied.
 *
 * <p>
 * Example, with single pair of fields: <br>
 *
 * @FieldDependency(first = "firstName", second = "lastName", message = "At least on value should be supplied")
 * </p>
 *
 * <p>
 * Example, with more than 1 pair of fields: <br>
 *
 * @FieldDependency.List({
 *      @FieldDependency(first = "firstName", second = "lastName", message = "At least on value should be supplied"),
 *      @FieldDependency(first = "email", second = "mobileNumber", message = "At least on value should be supplied")
 * })
 * </p>
 * @since 1.0.0
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldDependencyValidator.class)
@Documented
public @interface FieldDependency {

    String message() default "validation.data.constraints.msg.20002";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return The first field
     */
    String first();

    /**
     * @return The second field
     */
    String second();

    /**
     * Defines several <code>@FieldDependency</code> annotations on the same element
     */
    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        FieldDependency[] value();
    }
}
