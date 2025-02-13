package com.example.jinder.util;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class TokenUtil {
    public String createToken() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}
