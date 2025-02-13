package com.example.jinder.controller;

import com.example.jinder.dto.AuthDto;
import com.example.jinder.dto.SignUpDto;
import com.example.jinder.service.MailService;
import com.example.jinder.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserJinderController {

    private final UserService userService;
    private final MailService mailService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpDto dto){
        log.debug("Входящие параметры: {}", dto);
        userService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Код подтверждения отправлен на вашу почту");
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AuthDto dto){
       userService.authenticate(dto);
       return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("send-mail")
    public void sendMessage(){
        mailService.sendMail("gilmanovamir19@gmail.com", "HELLO FROM JINDER", "MY NAME IS GUSTAVO");
    }
}
