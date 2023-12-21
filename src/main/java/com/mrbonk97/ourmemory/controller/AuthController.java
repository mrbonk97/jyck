package com.mrbonk97.ourmemory.controller;

import com.mrbonk97.ourmemory.dto.Response;
import com.mrbonk97.ourmemory.dto.auth.request.*;
import com.mrbonk97.ourmemory.dto.auth.response.*;
import com.mrbonk97.ourmemory.model.User;
import com.mrbonk97.ourmemory.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    @PostMapping("/validate-email")
    public Response<ValidateEmailResponse> validateEmail(@RequestBody ValidateEmailRequest validateEmailRequest) throws ExecutionException, InterruptedException {
        CompletableFuture<String> code = authService.sendAuthEmail(validateEmailRequest.getEmail());
        return Response.success(ValidateEmailResponse.fromCode(code));
    }

    @PostMapping("/reset-password")
    public Response<String> resetPasswordEmailSend(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        System.out.println("이메일 들어옴 ");
        System.out.println(resetPasswordRequest.getEmail());
        System.out.println("이메일 들어옴 끝");
        authService.resetPassword(resetPasswordRequest.getEmail());
        return Response.success("이메일 발송 완료");
    }

    @PostMapping("/validate-code")
    public Response<Boolean> validateCodeExpiration(@RequestParam("code") String code) {
        boolean isCodeValid = authService.validateCodeExpiration(code);
        return Response.success(isCodeValid);
    }

    @PostMapping("/change-password")
    public Response<String> changePassword(@RequestParam("code") String code, @RequestBody ChangePasswordRequest changePasswordRequest) {
        authService.changePassword(code, changePasswordRequest.getPassword());
        return Response.success("패스워드 변경 완료");
    }

    @GetMapping("/redis-test")
    public void redisTest() {
        authService.redisTest();
    }
}
