package com.yanolja_final.global.config.security;

import com.yanolja_final.domain.user.entity.Authority;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.domain.user.repository.UserRepository;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * @author ghrltjdtprbs
 * @implNote
 *
 * OAuth2 client라이브러리에서 redirect된 경로의 로그인 성공 후 후처리를 하는 클래스
 * 로그인 성공 시 accesstoken과 사용자 정보를 같이 지급받게 되며,
 * 발급받은 accesstoken 및 사용자 정보를 아래와 같이 코드로 확인할 수 있다.
 *
 * System.out.println(" getClientRegistration : " + userRequest.getClientRegistration ());
 * System.out.println("getAccessToken: " + userRequest.getAccessToken());
 * System.out.println("getAttributes: " + super.loadUser(userRequest).getAttributes())
 */

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public static final Set<Authority> DEFAULT_AUTHORITIES =
        Collections.singleton(new Authority("ROLE_USER"));

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oauth2User = super.loadUser(userRequest);
        OAuth2UserInfo oauth2Userinfo = null;
        String provider = userRequest.getClientRegistration()
            .getRegistrationId();

        if (provider.equals("google")) {
            oauth2Userinfo = new GoogleUserInfo(oauth2User.getAttributes());
        } else if (provider.equals("kakao")) {
            oauth2Userinfo = new KakaoUserInfo((Map) oauth2User.getAttributes().get("response"));
        }

        Optional<User> user = userRepository.findByEmailAndProvider(
            oauth2Userinfo.getEmail(), oauth2Userinfo.getProvider());

        //이미 소셜로그인을 한적이 있는지 없는지
        if (user.isEmpty()) {
            User newUser = User.builder()
                .email(oauth2Userinfo.getEmail())
                .username(oauth2Userinfo.getName())
                .encryptedPassword("OAuth2")  //Oauth2로 로그인을 해서 패스워드는 의미없음.
                .phoneNumber(oauth2Userinfo.getPhoneNumber())
                .authorities(DEFAULT_AUTHORITIES)
                .provider(provider)
                .build();

            userRepository.save(newUser);
            return new PrincipalDetails(newUser, oauth2User.getAttributes());
        } else {
            return new PrincipalDetails(user.get(), oauth2User.getAttributes());
        }
    }
}
