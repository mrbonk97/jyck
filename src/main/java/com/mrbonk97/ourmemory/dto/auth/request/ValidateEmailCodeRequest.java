package com.mrbonk97.ourmemory.dto.auth.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateEmailCodeRequest {
    private String email;
    private String authCode;
}
