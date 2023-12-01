package com.mrbonk97.ourmemory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    @GetMapping("/mail")
    public void sendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo("hyunsuk1997@naver.com");
        message.setSubject("subject");
        message.setText("text");
        emailSender.send(message);
    }
}
