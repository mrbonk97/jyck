package com.mrbonk97.ourmemory.dto.auth.request;

import lombok.Getter;

@Getter
public class AuthLoginRequest {
    private String email;
    private String password;
}
