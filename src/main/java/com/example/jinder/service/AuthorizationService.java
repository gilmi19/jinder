package com.example.jinder.service;

import com.example.jinder.dto.SignInDto;
import com.example.jinder.entity.UserJinder;
import com.example.jinder.enums.TokenType;
import com.example.jinder.exception.InvalidTokenForUserException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizationService {
    private final TokenService tokenService;
    private final SessionService sessionService;
    private final UserService userService;

    @Transactional
    public void signIn(SignInDto dto) {
        UserJinder user = userService.findByNicknameAndPassword(dto.nickname(), dto.password());
        String sessionToken = tokenService.createToken(TokenType.SIGN_IN);
        log.info("Created session token: {}", sessionToken);
        sessionService.create(sessionToken, user);
    }

    @Transactional
    public void signOut(String sessionToken) {
        if (!sessionService.existsByToken(sessionToken)) {
            throw new InvalidTokenForUserException("Сессия уже была завершена или был передан неверный токен");
        }
        sessionService.deleteByToken(sessionToken);
    }
}
