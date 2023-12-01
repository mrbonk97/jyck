package com.mrbonk97.ourmemory.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@Getter
@Setter
public class AuthEmailResponse {
    private final  String email;
    private final boolean result;

    public static AuthEmailResponse fromArgument(String email, boolean result) {
        return new AuthEmailResponse(email, result);

    }
}
