package com.example.jinder.dto;

import com.example.jinder.enums.Gender;

public record SignUpDto(
        String email,
        String password,
        String nickname,
        Gender gender,
        String description
) {
}
