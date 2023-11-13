package com.mrbonk97.ourmemory.dto.auth.response;

import com.mrbonk97.ourmemory.model.MediaFile;
import com.mrbonk97.ourmemory.model.Provider;
import com.mrbonk97.ourmemory.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthSignupResponse {
    private Long id;
    private String email;
    private String name;
    private MediaFile profileImage;
    private Provider provider;
    private String providerId;

    public static AuthSignupResponse fromUser(User user) {
        AuthSignupResponse authSignupResponse = new AuthSignupResponse();
        authSignupResponse.setId(user.getId());
        authSignupResponse.setEmail(user.getEmail());
        authSignupResponse.setName(user.getName());
        authSignupResponse.setProfileImage(user.getProfileImage());
        authSignupResponse.setProvider(user.getProvider());
        authSignupResponse.setProviderId(user.getProviderId());
        return authSignupResponse;
    }
}

