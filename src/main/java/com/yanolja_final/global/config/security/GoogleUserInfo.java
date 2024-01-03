package com.yanolja_final.global.config.security;

import java.util.Map;

/**
 * @author ghrltjdtprbs
 * @implNote OAuth2 구글 로그인 후 받아온 값에서 사용자 정보를 저장하기 위한 클래스 입니다.
 * 구글,카카오,페이스북 등 로그인하고자 하는 대상별로 제공하는 세부 값들이 다르기에 따로 클래스를 생성했습니다.
 */

public class GoogleUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getPhoneNumber() {
        return null;
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email")+"OAuth2";
    }

    @Override
    public String getProvider() {
        return "google";
    }
}
