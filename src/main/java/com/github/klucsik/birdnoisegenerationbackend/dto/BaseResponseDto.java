package com.github.klucsik.birdnoisegenerationbackend.dto;

import lombok.Data;

import java.util.Map;

@Data
public class BaseResponseDto {
    String message;
    Map<String, String> errors;
}
