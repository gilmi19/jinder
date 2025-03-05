package com.example.jinder.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserShowDto(
        @Schema(description = "Имя пользователя", example = "Валерий")
        String nickname,
        @Schema(description = "Описание пользователя", example = "люблю котиков и цветы")
        String description
) {
}
