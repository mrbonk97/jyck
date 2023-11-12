package com.mrbonk97.ourmemory.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @PostMapping("/sign-up")
    public void createUser() {}

    @PostMapping("/log-in")
    public void loginUser() {}
}
