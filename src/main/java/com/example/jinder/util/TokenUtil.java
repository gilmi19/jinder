package com.example.jinder.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Getter
public class TokenUtil {
    private final static String TOKEN_PREFIX = "gilmi-";
    private final static String TOKEN_POSTFIX = "-token";

    public String createAuthToken() {
        return TOKEN_PREFIX + UUID.randomUUID().toString().substring(0, 6) + TOKEN_POSTFIX; //18
    }

    public String createSignInToken() {
        return TOKEN_PREFIX + UUID.randomUUID().toString().substring(0, 10) + TOKEN_POSTFIX; // 22
    }
}
