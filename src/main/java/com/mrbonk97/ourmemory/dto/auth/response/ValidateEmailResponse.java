package com.mrbonk97.ourmemory.dto.auth.response;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Setter
@Getter
public class ValidateEmailResponse {
    private String code;
    public static ValidateEmailResponse fromCode(CompletableFuture<String> code) throws ExecutionException, InterruptedException {
        ValidateEmailResponse validateEmailResponse = new ValidateEmailResponse();
        validateEmailResponse.setCode(code.get());
        return validateEmailResponse;
    }
}
