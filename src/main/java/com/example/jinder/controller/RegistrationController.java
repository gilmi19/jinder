package com.example.jinder.controller;

import com.example.jinder.dto.SignUpDto;
import com.example.jinder.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sign-up")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Контроллер для аутентификации")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping()
    @Operation(summary = "Регистрация")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "text/plain"))
    public ResponseEntity<String> signUp(@RequestBody SignUpDto dto) {
        log.debug("Входящие параметры: {}", dto);
        registrationService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Код подтверждения отправлен на вашу почту");
    }
}
