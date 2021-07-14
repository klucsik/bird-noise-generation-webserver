package com.github.klucsik.birdnoiseserver.backendserver.validators.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TrimmedValidator implements ConstraintValidator<Trimmed, Object> {
    @Override
    public void initialize(Trimmed constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        return value.toString().equals(value.toString().trim());
    }
}
