package com.github.klucsik.birdnoisegenerationbackend.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = TrimmedValidator.class)
@Documented
public @interface Trimmed {
    String message() default "Leading or trailing whitespaces not allowed!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

