package com.mrbonk97.ourmemory.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthCodeValidationResponse {
    private boolean isCodeValid;
}
