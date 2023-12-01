package com.mrbonk97.ourmemory.oauth2.user;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class NaverOAuth2UserInfo extends OAuth2UserInfo{

    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super((Map<String, Object>) attributes.get("response"));
        System.out.println("네이버 1");
        System.out.println(attributes);
    }

    @Override
    public String getId() {
        System.out.println("네이버 아이디: " + attributes.get("id").toString());
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        System.out.println("네이버 이메일: " + attributes.get("email").toString());
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        System.out.println("네이버 사진: " + attributes.get("profile_image").toString());
        return (String) attributes.get("profile_image");
    }

    @Override
    public String getName() {
        System.out.println("네이버 사진: " + attributes.get("name").toString());
        return (String) attributes.get("name");
    }
}

