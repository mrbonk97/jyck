package com.mrbonk97.ourmemory.dto.auth.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidateEmailResponse {
    private String code;
    public static ValidateEmailResponse fromCode(String code) {
        ValidateEmailResponse validateEmailResponse = new ValidateEmailResponse();
        validateEmailResponse.setCode(code);
        return validateEmailResponse;
    }
}
