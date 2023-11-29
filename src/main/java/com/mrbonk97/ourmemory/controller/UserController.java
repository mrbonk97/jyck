package com.mrbonk97.ourmemory.controller;

import com.mrbonk97.ourmemory.dto.Response;
import com.mrbonk97.ourmemory.dto.auth.request.AuthSignupRequest;
import com.mrbonk97.ourmemory.dto.user.response.UserResponse;
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
    public Response<UserResponse> getUser(Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());
        User user = userService.getUser(userId);
        return Response.success(UserResponse.fromUser(user));
    }

    @PutMapping("/me")
    public Response<UserResponse> updateUser(Authentication authentication,@RequestBody AuthSignupRequest authSignupRequest) {
        Long userId = Long.valueOf(authentication.getName());
        User user = userService.updateUser(
                userId,
                authSignupRequest.getEmail(),
                authSignupRequest.getName(),
                authSignupRequest.getPassword(),
                authSignupRequest.getPhoneNumber(),
                authSignupRequest.getProfileImage()
        );

        return Response.success(UserResponse.fromUser(user));
    }

    @DeleteMapping("/me")
    public Response<String> deleteUser(Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());
        userService.deleteUser(userId);
        return Response.success("삭제 완료.");
    }
}
