package com.example.jinder.service;

import com.example.jinder.entity.Token;
import com.example.jinder.entity.UserJinder;
import com.example.jinder.enums.TokenType;
import com.example.jinder.exception.InvalidTokenForUserException;
import com.example.jinder.exception.UnsupportedTokenType;
import com.example.jinder.repository.TokenRepository;
import com.example.jinder.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final TokenUtil tokenUtil = new TokenUtil();
    private final MailService mailService;
    private final static String SUBJECT_TEXT = "Ваш токен для подтверждения";
    private final UserService userService;

    public void save(UserJinder userJinder, String tokenValue, LocalDateTime createdAt) {
        Token token = Token.builder()
                .userJinder(userJinder)
                .token(tokenValue)
                .createdAt(createdAt)
                .expirationDate(createdAt.plusHours(1))
                .build();
        tokenRepository.save(token);
    }

    public boolean isTokenExpired(Token token) {
        return LocalDateTime.now().isAfter(token.getExpirationDate());
    }

    public void deleteByUserEmail(String email) {
        tokenRepository.deleteByUserJinder_Email(email);
    }

    public Token findByUserEmailAndPassword(String email, String password) {
        return tokenRepository.findByUserJinder_EmailAndUserJinder_Password(email, password)
                .orElseThrow(() -> new InvalidTokenForUserException("Введенный токен не верный"));
    }

    public void sendToken(String email, String token) {
        mailService.sendMail(email, SUBJECT_TEXT, token);
    }

    public String createToken(TokenType tokenType) {
        return switch (tokenType) {
            case AUTH -> tokenUtil.createAuthToken();
            case SIGN_IN -> tokenUtil.createSignInToken();
            default -> throw new UnsupportedTokenType("Неизвестный тип токена");
        };
    }

    public void sendAndSaveToken(String email, String token) {
        UserJinder user = userService.findByEmail(email);
        sendToken(email, token);
        save(user, token, LocalDateTime.now());
    }
}
