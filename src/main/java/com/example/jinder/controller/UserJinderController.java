package com.example.jinder.controller;

import com.example.jinder.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserJinderController {
    private final UserService userService;

    @DeleteMapping("self-delete")
    public void selfDelete(@RequestParam String nickname) {
        userService.selfDelete(nickname);
    }
}
