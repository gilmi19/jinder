package com.example.jinder.controller;

import com.example.jinder.dto.AuthDto;
import com.example.jinder.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping()
    public ResponseEntity<?> auth(@RequestBody AuthDto dto) {
        authenticationService.authenticate(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/reset-token")
    public void resetToken(@RequestParam String email) {
        authenticationService.refreshAuthToken(email);
    }
}
