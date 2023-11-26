package com.mrbonk97.ourmemory.controller;

import com.mrbonk97.ourmemory.dto.auth.request.AuthSignupRequest;
import com.mrbonk97.ourmemory.model.User;
import com.mrbonk97.ourmemory.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v2/users")
@RestController
public class UserController {
    private final UserService userService;


    @GetMapping("/me")
    public User getUser(Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());
        return userService.getUser(userId);
    }

    @PutMapping("/me")
    public User updateUser(Authentication authentication,@RequestBody AuthSignupRequest authSignupRequest) {
        Long userId = Long.valueOf(authentication.getName());
        log.error("들어왔는지" + userId);
        return userService.updateUser(
                userId,
                authSignupRequest.getEmail(),
                authSignupRequest.getName(),
                authSignupRequest.getPassword(),
                authSignupRequest.getPhoneNumber(),
                authSignupRequest.getProfileImage()
        );
    }

    @DeleteMapping("/me")
    public void deleteUser(Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());
        userService.deleteUser(userId);
    }
}
