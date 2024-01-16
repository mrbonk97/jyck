package com.mrbonk97.ourmemory.controller;

import com.mrbonk97.ourmemory.service.RedisService;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@AllArgsConstructor
@RestController
public class TestController {
    private JavaMailSender mailSender;
    private RedisService redisService;
    
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/sendMail")
    public String sendConfirmationEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("aaa@localhost");
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World from Spring Boot Email");
        mailSender.send(msg);
        return "OK";
    }

    @GetMapping("/redis")
    public void redisTest() {

        

    }
}
