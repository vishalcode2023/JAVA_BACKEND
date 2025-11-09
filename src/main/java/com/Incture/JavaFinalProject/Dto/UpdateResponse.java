package com.Incture.JavaFinalProject.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateResponse {
    private String name;
    private String email;
    private String password;
}
