package com.mrbonk97.ourmemory.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AuthLoginResponse {
    private String jwtToken;

    public static AuthLoginResponse fromToken(String jwtToken) {
        return new AuthLoginResponse(jwtToken);
    }
}
