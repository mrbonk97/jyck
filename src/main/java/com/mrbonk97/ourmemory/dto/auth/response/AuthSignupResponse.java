package com.mrbonk97.ourmemory.dto.auth.response;

import com.mrbonk97.ourmemory.model.MediaFile;
import com.mrbonk97.ourmemory.model.AuthProvider;
import com.mrbonk97.ourmemory.model.User;
import com.mrbonk97.ourmemory.utils.JwtTokenUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthSignupResponse {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private MediaFile profileImage;
    private AuthProvider authProvider;
    private String providerId;
    private String jwtToken;
    public static AuthSignupResponse fromUser(User user) {
        AuthSignupResponse authSignupResponse = new AuthSignupResponse();
        authSignupResponse.setId(user.getId());
        authSignupResponse.setEmail(user.getEmail());
        authSignupResponse.setName(user.getName());
        authSignupResponse.setPhoneNumber(user.getPhoneNumber());
        authSignupResponse.setProfileImage(user.getProfileImage());
        authSignupResponse.setAuthProvider(user.getAuthProvider());
        authSignupResponse.setProviderId(user.getProviderId());
        authSignupResponse.setJwtToken(JwtTokenUtils.generateToken(user.getId()));
        return authSignupResponse;
    }
}

