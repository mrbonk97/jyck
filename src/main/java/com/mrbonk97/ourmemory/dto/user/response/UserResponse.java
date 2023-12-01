package com.mrbonk97.ourmemory.dto.user.response;

import com.mrbonk97.ourmemory.model.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private MediaFile profileImage;
    private AuthProvider authProvider;
    private String providerId;

    public static UserResponse fromUser(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setName(user.getName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setProfileImage(user.getProfileImage());
        userResponse.setAuthProvider(user.getAuthProvider());
        userResponse.setProviderId(user.getProviderId());
        return userResponse;
    }
}
