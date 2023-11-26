package com.mrbonk97.ourmemory.dto.auth.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthSignupRequest {
    private String email;
    private String name;
    private String password;
    private String phoneNumber;
    private String profileImage;
}
