package com.example.jinder.service;

import com.example.jinder.dto.SignUpDto;
import com.example.jinder.entity.UserJinder;
import com.example.jinder.enums.Gender;
import com.example.jinder.mapper.UserJinderMapper;
import com.example.jinder.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserJinderMapper mapper;

    public void save(UserJinder userJinder) {
        userRepository.save(userJinder);
    }

    public void create(SignUpDto dto) {
        checkUserExisting(dto);
        UserJinder entity = mapper.toEntity(dto);
        userRepository.save(entity);
        log.info("Сохраненная сущность - {}", entity);
    }

    public UserJinder createTest(SignUpDto dto) {
        checkUserExisting(dto);
        UserJinder entity = mapper.toEntity(dto);
        userRepository.save(entity);
        log.info("Сохраненная сущность - {}", entity);
        return entity;
    }

    public UserJinder findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElseThrow(() ->
                new EntityNotFoundException("Неверный пароль или email"));
    }

    public UserJinder findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Такой email не зарегистрирован"));
    }

    public UserJinder findUnviewedUser(Gender gender) {
        return userRepository
                .findUnviewedUser(gender)
                .orElseThrow(() -> new EntityNotFoundException("больше пользователей нет"));
    }

    public UserJinder findByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с таким nickname не найден"));
    }

    @Transactional
    public void selfDelete(String nickName) {
        userRepository.deleteByNickname(nickName);
    }

    public UserJinder findByNicknameAndPassword(String nickname, String password) {
        return userRepository.findByNicknameAndPassword(nickname, password)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с таким именем или паролем не найден"));
    }

    private void checkUserExisting(SignUpDto dto) {
        boolean isExist = userRepository.findByNicknameOrEmail(dto.nickname(), dto.email()).isPresent();
        if (isExist) {
            throw new EntityExistsException("Пользователь с таким именем или почтой уже существует");
        }
    }
}
