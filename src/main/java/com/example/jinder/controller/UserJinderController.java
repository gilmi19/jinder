package com.example.jinder.controller;

import com.example.jinder.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Контроллер для управления пользователем")
public class UserJinderController {
    private final UserService userService;

    @Operation(summary = "Полное удаление информации о пользователе")
    @ApiResponse(responseCode = "200")
    @DeleteMapping("/self-delete")
    public void selfDelete(@Parameter(description = "Имя пользователя") @RequestParam String nickname) {
        userService.selfDelete(nickname);
    }
}
