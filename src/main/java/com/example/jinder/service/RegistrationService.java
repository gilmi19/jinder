package com.example.jinder.service;

import com.example.jinder.dto.SignUpDto;
import com.example.jinder.entity.UserJinder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.jinder.enums.TokenType.AUTH;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {
    private final UserService userService;
    private final TokenService tokenService;

    @Transactional
    public void signUp(SignUpDto dto) {
        //userService.create(dto);
        UserJinder userJinder = userService.createTest(dto);
        String authToken = tokenService.createToken(AUTH);
        //tokenService.sendAndSaveToken(dto.email(), authToken);
        tokenService.sendToken(dto.email(), authToken);
        tokenService.save(userJinder, authToken, LocalDateTime.now());
    }
}
