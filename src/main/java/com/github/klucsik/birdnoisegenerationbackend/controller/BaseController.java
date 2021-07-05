package com.github.klucsik.birdnoisegenerationbackend.controller;

import com.github.klucsik.birdnoisegenerationbackend.dto.BaseResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    //TODO: Decide the format, how shall we send back the errors. Just this Map, or make messages for toasts aswell?
    //TODO Maybe we can make a baseResponseEntity with a message field and an errors field

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponseDto handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        BaseResponseDto responseDto = new BaseResponseDto();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            responseDto.setMessage("Validation error!");
            responseDto.setErrors(errors);

        });
        return responseDto;
    }
}
