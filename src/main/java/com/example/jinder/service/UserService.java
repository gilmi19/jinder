package com.example.jinder.service;

import com.example.jinder.UserJinderMapper;
import com.example.jinder.dto.AuthDto;
import com.example.jinder.dto.SignUpDto;
import com.example.jinder.entity.Token;
import com.example.jinder.entity.UserJinder;
import com.example.jinder.exception.InvalidTokenForUserException;
import com.example.jinder.exception.TokenExistTimeException;
import com.example.jinder.repository.TokenRepository;
import com.example.jinder.repository.UserRepository;
import com.example.jinder.util.TokenUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserJinderMapper mapper;
    private final TokenUtil tokenUtil = new TokenUtil();
    private final MailService mailService;
    private final static String SUBJECT_TEXT = "Ваш токен для подтверждения";
    private final TokenService tokenService;
    private final TokenRepository tokenRepository;

    @Transactional
    public void signUp(SignUpDto dto) {
        log.debug("Входящие параметры: {}", dto.email());
        checkUserExisting(dto);
        UserJinder entity = mapper.toEntity(dto);
        userRepository.save(entity);
        String token = tokenUtil.createToken();
        sendToken(dto.email(), token);
        log.info("Сохраненная сущность - {}", entity);
        tokenService.save(entity, token, LocalDateTime.now());
    }

    public void authenticate(AuthDto dto) {
        UserJinder userJinder = userRepository.findByEmailAndPassword(dto.email(), dto.password()).orElseThrow(() ->
                new EntityNotFoundException("Неверный пароль или email"));

        Token token = tokenRepository.findByUserJinder_EmailAndUserJinder_Password(dto.email(), dto.password())
                .orElseThrow(() -> new InvalidTokenForUserException("Введенный токен не верный"));
        if (tokenService.isTokenExpired(dto.token(), token)) {
            throw new TokenExistTimeException("Время действия токена истекло");
        }
        userJinder.setIsVerified(true);
        userRepository.save(userJinder);
    }

//    public boolean isUserJinderExist(String email, String password) {
//        return userRepository.findByEmailAndPassword(email, password).isPresent();
//    }

    private void sendToken(String email, String token) {
        mailService.sendMail(email, SUBJECT_TEXT, token);
    }

    private void checkUserExisting(SignUpDto dto) {
        boolean isExist = userRepository.findByNicknameOrEmail(dto.nickname(), dto.email()).isPresent();
        if (isExist) {
            throw new EntityExistsException("Пользователь с таким именем или почтой уже существует");
        }
    }
    //TODO: сделать метод изменить токен

    //TODO: сделать регистрацию, аутентификацию, авторизацию по плану: 1)он отправляет данные для регистрации(String email,String password , String nickname, Gender gender, String description).
    //2) проверяем поля на уникальность(email, nickname)
    //3) если все окей то регистрируем пользователя(в http ответе отправляем токен для аутентификации - http ответ имитирует письмо на почту)
    //4) дальше ему надо аутентифицироваться(вводит проверочный токен и получается регистрация завершена)
    //5) авторизация - ввод имени и пароля (проверка на существование пользователя с таким именем и совпадение в пароле)

}
