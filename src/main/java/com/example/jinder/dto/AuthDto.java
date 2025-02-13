package com.example.jinder.dto;

public record AuthDto(
        String token,
        String email,
        String password
) {
}
