package com.example.jinder.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignInDto(
        @Schema(description = "Имя пользователя", example = "Валерий")
        String nickname,
        @Schema(description = "Пароль пользователя", example = "Mypass123!@")
        String password) {
}
