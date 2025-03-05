package com.example.jinder.dto;

import com.example.jinder.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;

public record SignUpDto(
        @Schema(description = "Email пользователя", example = "example@gmail.com")
        String email,
        @Schema(description = "Пароль пользователя", example = "Mypass123!@")
        String password,
        @Schema(description = "Имя пользователя", example = "Валерий")
        String nickname,
        @Schema(description = "Гендер(пол) пользователя", example = "MALE")
        Gender gender,
        @Schema(description = "Описание пользователя", example = "25 лет, студент")
        String description
) {
}
