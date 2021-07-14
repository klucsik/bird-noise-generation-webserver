package com.github.klucsik.birdnoiseserver.backendclient.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class BaseResponseDto {
    String message;
    Map<String, String> errors;
    public BaseResponseDto(String message) {
        this.message = message;
    }
}
