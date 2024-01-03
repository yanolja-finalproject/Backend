package com.yanolja_final.global.config.security;

import com.yanolja_final.global.config.security.jwt.JwtProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author ghrltjdtprbs
 * @implNote 이것은 OAuth 로그인 성공 후 로직을 처리 하는 클래스 입니다.
 *
 * 로그인 성공 후 프론트 페이지로 리디렉트 하게 설정하였습니다.
 *
 * 스프링 코드 내로 리디렉트 설정 하고 싶은 경우
 * String redirectUrl = "/user/oauth-success?token="+token;
 *
 */

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String token = jwtProvider.createToken(principalDetails.getUser());
        String email = principalDetails.getEmail();
        String name = principalDetails.getUsername();

        //한국어 인코딩 설정
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());

        String redirectUrl = "프론트 도메인" + token
            +"&email="+email+"&name="+encodedName;
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
