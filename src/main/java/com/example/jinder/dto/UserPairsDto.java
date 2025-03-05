package com.example.jinder.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserPairsDto(
        @Schema(description = "Имя пользователя", example = "Валерий")
        String name,
        @Schema(description = "Описание пользователя", example = "люблю котиков и цветы")
        String description,
        @Schema(description = "Email пользователя", example = "example@gmail.com")
        String email
) {
}
