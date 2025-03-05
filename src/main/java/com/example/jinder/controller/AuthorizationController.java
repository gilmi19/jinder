package com.example.jinder.controller;

import com.example.jinder.dto.SignInDto;
import com.example.jinder.service.AuthorizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Контроллер для аутентификации")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/sign-in")
    @Operation(summary = "Войти в систему")
    @ApiResponse(responseCode = "200")
    public void signIn(@RequestBody SignInDto dto) {
        authorizationService.signIn(dto);
    }


    @PostMapping("/sign-out")
    @Operation(summary = "Выти из системы")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "text/plain"))
    public ResponseEntity<String> signOut(@Parameter(description = "Токен сессии") @RequestHeader String sessionToken) {
        authorizationService.signOut(sessionToken);
        return ResponseEntity.status(HttpStatus.OK).body("Вы успешно вышли из сессии");
    }
}
