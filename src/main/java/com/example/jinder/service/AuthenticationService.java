package com.example.jinder.service;

import com.example.jinder.dto.AuthDto;
import com.example.jinder.entity.Token;
import com.example.jinder.entity.UserJinder;
import com.example.jinder.enums.TokenType;
import com.example.jinder.exception.TokenExistTimeException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final TokenService tokenService;

    public void authenticate(AuthDto dto) {
        Token userAuthToken = tokenService.findByUserEmailAndPassword(dto.email(), dto.password());
        if (tokenService.isTokenExpired(userAuthToken)) {
            throw new TokenExistTimeException("Время действия токена истекло");
        }
        UserJinder user = userService.findByEmailAndPassword(dto.email(), dto.password());
        user.setIsVerified(true);
        userService.save(user);
    }

    @Transactional()
    public void refreshAuthToken(String email) {
        tokenService.deleteByUserEmail(email);
        String token = tokenService.createToken(TokenType.AUTH);
        tokenService.sendAndSaveToken(email, token);
    }
}
