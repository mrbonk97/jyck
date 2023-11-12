package com.mrbonk97.ourmemory.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
public class UserController {

    @GetMapping("/me")
    public  void getUser() {}

    @PutMapping("/me")
    public void updateUser() {}

    @DeleteMapping("/me")
    public void deleteUser() {}
}
