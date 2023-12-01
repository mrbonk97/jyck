package com.mrbonk97.ourmemory.oauth2.user;

import com.mrbonk97.ourmemory.Exception.ErrorCode;
import com.mrbonk97.ourmemory.Exception.OurMemoryException;
import com.mrbonk97.ourmemory.model.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        System.out.println(registrationId);

        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        }
        else if(registrationId.equalsIgnoreCase(AuthProvider.naver.toString())) {
            return new NaverOAuth2UserInfo(attributes);
        }
        else {
            throw new OurMemoryException(ErrorCode.OAUTH2USER_ERROR);
        }
    }
}