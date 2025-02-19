package com.example.jinder.controller;

import com.example.jinder.dto.SignUpDto;
import com.example.jinder.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpDto dto) {
        log.debug("Входящие параметры: {}", dto);
        registrationService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Код подтверждения отправлен на вашу почту");
    }
}
