package com.example.jinder.controller;

import com.example.jinder.dto.UserPairsDto;
import com.example.jinder.dto.UserShowDto;
import com.example.jinder.service.UserInteractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Методы пользователя для работы с приложением")
public class UserInteractionController {

    private final UserInteractionService userInteractionService;

    @PostMapping("/show")
    @Operation(summary = "Показать пользователей противоположного пола")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserShowDto.class)
            )
    )
    public UserShowDto show(@Parameter(description = "Токен сессии") @RequestHeader String sessionToken) {
        return userInteractionService.show(sessionToken);
    }

    @PostMapping("/like")
    @Operation(summary = "Поставить лайк пользователю")
    public void like(
            @Parameter(description = "Токен сессии") @RequestHeader String sessionToken,
            @Parameter(description = "Имя пользователя, который понравился") @RequestParam String nickname,
            @Parameter(description = "Лайкает первый или второй пользователь") @RequestParam boolean isSecondUser) {
        userInteractionService.like(sessionToken, nickname, isSecondUser);
    }

    @GetMapping("/get-pairs")
    @Operation(summary = "Показать пары пользователя")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = UserPairsDto.class)
                    )
            )
    )
    public List<UserPairsDto> getPairs(@Parameter(description = "Токен сессии") @RequestHeader String sessionToken) {
        return userInteractionService.getPairs(sessionToken);
    }
}
