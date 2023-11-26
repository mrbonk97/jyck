package com.mrbonk97.ourmemory.controller;

import com.mrbonk97.ourmemory.dto.Response;
import com.mrbonk97.ourmemory.dto.auth.request.AuthLoginRequest;
import com.mrbonk97.ourmemory.dto.auth.request.AuthSignupRequest;
import com.mrbonk97.ourmemory.dto.auth.response.AuthLoginResponse;
import com.mrbonk97.ourmemory.dto.auth.response.AuthSignupResponse;
import com.mrbonk97.ourmemory.model.User;
import com.mrbonk97.ourmemory.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v2/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public Response<AuthSignupResponse> createUser(@RequestBody AuthSignupRequest authSignupRequest){
        User createdUser = authService.createUser(
                authSignupRequest.getEmail(),
                authSignupRequest.getName(),
                authSignupRequest.getPassword(),
                authSignupRequest.getPhoneNumber(),
                authSignupRequest.getProfileImage()
        );
        return Response.success(AuthSignupResponse.fromUser(createdUser));
    }

    @PostMapping("/log-in")
    public Response<AuthLoginResponse> loginUser(@RequestBody AuthLoginRequest authLoginRequest) {
        String jwtToken = authService.loginUser(authLoginRequest.getEmail(), authLoginRequest.getPassword());
        return Response.success(AuthLoginResponse.fromToken(jwtToken));
    }

}
