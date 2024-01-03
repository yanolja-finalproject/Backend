package com.yanolja_final.global.config.security;

import java.util.Map;

/**
 * @author ghrltjdtprbs
 * @implNote OAuth2 카카오 로그인 후 받아온 값에서 사용자 정보를 저장하기 위한 클래스 입니다.
 * 구글,카카오,페이스북 등 로그인하고자 하는 대상별로 제공하는 세부 값들이 다르기에 따로 클래스를 생성했습니다.
 */

public class KakaoUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        if (attributes.containsKey("kakao_account")) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            return (String) kakaoAccount.get("email");
        }
        return null;
    }

    @Override
    public String getName() {
        if (attributes.containsKey("properties")) {
            Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
            return (String) properties.get("nickname");
        }
        return null;
    }

    @Override
    public String getPhoneNumber() {
        // 카카오 API에서 전화번호 정보를 제공하지 않는 경우, null 반환
        return null;
    }
}
