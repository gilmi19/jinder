package com.example.jinder.service;

import com.example.jinder.entity.Token;
import com.example.jinder.entity.UserJinder;
import com.example.jinder.repository.TokenRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public void save(UserJinder userJinder, String tokenValue, LocalDateTime createdAt) {
        Token token = Token.builder()
                .userJinder(userJinder)
                .token(tokenValue)
                .createdAt(createdAt)
                .expirationDate(createdAt.plusHours(1))
                .build();
        tokenRepository.save(token);
    }

    public boolean isTokenExpired(String tokenValue) {
        Token token = tokenRepository.findByToken(tokenValue)
                .orElseThrow(() -> new EntityNotFoundException("Такой токен не существует"));
        return LocalDateTime.now().isAfter(token.getExpirationDate());
    }

    public boolean isTokenExpired(String tokenValue, Token token) {
        return LocalDateTime.now().isAfter(token.getExpirationDate());
    }
}
