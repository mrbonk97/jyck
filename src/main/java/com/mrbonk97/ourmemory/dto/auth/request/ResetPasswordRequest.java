package com.mrbonk97.ourmemory.dto.auth.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResetPasswordRequest {
    private String email;
}
