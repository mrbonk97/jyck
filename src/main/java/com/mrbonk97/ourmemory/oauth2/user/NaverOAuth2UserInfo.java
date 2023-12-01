package com.mrbonk97.ourmemory.oauth2.user;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo{

    public NaverOAuth2UserInfo(Map<String, Object> attributes) { super(attributes); }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getId() {
        return null;
    }


    public String getProviderId() {
        return attributes.get("id").toString();
    }

    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getImageUrl() {
        return null;
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }
}

