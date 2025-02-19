package com.example.jinder.controller;

import com.example.jinder.dto.SignInDto;
import com.example.jinder.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorization")
@RequiredArgsConstructor
@Slf4j
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/sign-in")
    public void signIn(@RequestBody SignInDto dto) {
        authorizationService.signIn(dto);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<String> signOut(@RequestHeader String sessionToken) {
        authorizationService.signOut(sessionToken);
        return ResponseEntity.status(HttpStatus.OK).body("Вы успешно вышли из сессии");
    }
}
