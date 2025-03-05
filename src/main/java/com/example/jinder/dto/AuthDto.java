package com.example.jinder.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthDto(
        @Schema(description = "Токен для верификации", example = "gilmi-abc12-token")
        String token,
        @Schema(description = "Email пользователя", example = "example@gmail.com")
        String email,
        @Schema(description = "Пароль пользователя", example = "Mypass123!@")
        String password
) {
}
