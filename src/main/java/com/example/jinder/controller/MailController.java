package com.example.jinder.controller;

import com.example.jinder.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    @PostMapping("send-mail")
    public void sendMessage() {
        mailService.sendMail("gilmanovamir19@gmail.com", "HELLO FROM JINDER", "MY NAME IS GUSTAVO");
    }
}
