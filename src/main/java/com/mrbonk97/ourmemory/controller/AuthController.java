package com.mrbonk97.ourmemory.controller;

import com.mrbonk97.ourmemory.dto.auth.request.AuthLoginRequest;
import com.mrbonk97.ourmemory.dto.auth.request.AuthSignupRequest;
import com.mrbonk97.ourmemory.dto.auth.response.AuthSignupResponse;
import com.mrbonk97.ourmemory.model.User;
import com.mrbonk97.ourmemory.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public AuthSignupResponse createUser(@RequestPart(value="file",required = false) MultipartFile profileImage, AuthSignupRequest authSignupRequest) throws IOException {
        User createdUser = authService.createUser(authSignupRequest.getEmail(), authSignupRequest.getPassword(), authSignupRequest.getName(), profileImage);
        return AuthSignupResponse.fromUser(createdUser);
    }

    @PostMapping("/log-in")
    public String loginUser(@RequestBody AuthLoginRequest authLoginRequest) {
        return "Bearer " + authService.loginUser(authLoginRequest.getEmail(), authLoginRequest.getPassword());
    }
    
}
