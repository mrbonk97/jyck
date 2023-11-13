package com.mrbonk97.ourmemory.controller;

import com.mrbonk97.ourmemory.dto.auth.request.AuthSignupRequest;
import com.mrbonk97.ourmemory.model.User;
import com.mrbonk97.ourmemory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserService userService;


    @GetMapping("/me")
    public User getUser(Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());
        return userService.getUser(userId);
    }

    @PutMapping("/me")
    public User updateUser(@RequestPart(value="file",required = false) MultipartFile profileImage, Authentication authentication, AuthSignupRequest authSignupRequest) throws IOException {
        System.out.println(profileImage);
        Long userId = Long.valueOf(authentication.getName());
        return userService.updateUser(
                userId,
                authSignupRequest.getEmail(),
                authSignupRequest.getPassword(),
                authSignupRequest.getName(),
                profileImage
        );
    }

    @DeleteMapping("/me")
    public void deleteUser(Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());
        userService.deleteUser(userId);
    }
}
