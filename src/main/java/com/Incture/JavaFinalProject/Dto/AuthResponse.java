package com.Incture.JavaFinalProject.Dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthResponse {
    private String token;
    private String message;
}
