package com.github.klucsik.birdnoisegenerationbackend.validators;


import org.springframework.stereotype.Component;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

/**
 * A base validation class which handles the validation excpetion throwing, and summing the annotation validations and custom validations.
 * Usage: Run custom validation logic in a validate( Object target) function, collect the errors in a list,
 * then call validateAnnotations(target,errorList). Don't need to return anything.
 */
@Component
public class BaseValidator {

    private final Validator validator;

    public BaseValidator(Validator validator) {
        this.validator = validator;
    }

    /**
     * Get the result of the custom validations as a param, and execute annotations. Send up the collectred errors in an exception.
     * @param target The class we want to run annotated validations on. Usually an Entity.
     * @param customValidationErrors A list of field errors which produced by custom validations.
     */
    public void validateAnnotations(Object target, List<FieldError> customValidationErrors) throws MethodArgumentNotValidException {

        BeanPropertyBindingResult result = new BeanPropertyBindingResult(target, "playUnit");

        validator.validate(target,result );
        // validation goes here
        customValidationErrors.forEach(result::addError);
        if (result.hasErrors()){throw new MethodArgumentNotValidException(null,result);}
    }
}
