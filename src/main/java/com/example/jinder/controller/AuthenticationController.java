package com.example.jinder.controller;

import com.example.jinder.dto.AuthDto;
import com.example.jinder.service.AuthenticationService;
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
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping()
    @Operation(summary = "Аутентифицировать")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "text/plain"))
    public ResponseEntity<?> auth(@RequestBody AuthDto dto) {
        authenticationService.authenticate(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Сбросить токен")
    @ApiResponse(responseCode = "200")
    @PostMapping("/reset-token")
    public void resetToken(
            @Parameter(description = "Почта, на которую выслать новый токен")
            @RequestParam String email) {
        authenticationService.refreshAuthToken(email);
    }
}
