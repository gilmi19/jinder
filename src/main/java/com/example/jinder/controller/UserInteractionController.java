package com.example.jinder.controller;

import com.example.jinder.dto.UserPairsDto;
import com.example.jinder.dto.UserShowDto;
import com.example.jinder.service.UserInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserInteractionController {

    private final UserInteractionService userInteractionService;

    @PostMapping("/show")
    public UserShowDto show(@RequestHeader String sessionToken) {
        return userInteractionService.show(sessionToken);
    }

    @PostMapping("like")
    public void like(
            @RequestHeader String sessionToken,
            @RequestParam String nickname,
            @RequestParam boolean isSecondUser) {
        userInteractionService.like(sessionToken, nickname, isSecondUser);
    }

    @GetMapping("get-pairs")
    public List<UserPairsDto> getPairs(@RequestHeader String sessionToken) {
        return userInteractionService.getPairs(sessionToken);
    }
}
