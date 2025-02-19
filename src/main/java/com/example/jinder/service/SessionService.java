package com.example.jinder.service;

import com.example.jinder.entity.Session;
import com.example.jinder.entity.UserJinder;
import com.example.jinder.exception.MissingTokenException;
import com.example.jinder.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;

    public void create(String sessionToken, UserJinder userJinder) {
        Session session = Session.builder()
                .token(sessionToken)
                .userJinder(userJinder)
                .build();
        sessionRepository.save(session);
    }

    public Session findByToken(String sessionToken) {
        return sessionRepository.findByToken(sessionToken)
                .orElseThrow(() -> new MissingTokenException("Токен не передан или недействителен"));
    }

    public void deleteByToken(String sessionToken) {
        sessionRepository.deleteByToken(sessionToken);
    }

    public boolean existsByToken(String sessionToken) {
        return sessionRepository.existsByToken(sessionToken);
    }
}
