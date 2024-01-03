package com.yanolja_final.global.config.security;

import com.yanolja_final.global.config.security.jwt.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] ALLOWED_PATHS
        = {
        "/v1/docs/**", "/v1/users/email/**", "/h2-console/**"
    };

    private final JwtFilter jwtFilter;
    private final PrincipalOauth2UserService principalOauth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // JWT 인증을 사용하므로 CSRF 보안 해제 및 세션 사용안함 설정
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
        ;

        // h2-console을 위한 FrameOption 허용
        http.headers(headers ->
            headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
        );

        http.exceptionHandling(exceptionHandling -> exceptionHandling
            .accessDeniedHandler(
                (request, response, accessDeniedException)
                    -> response.sendError(HttpServletResponse.SC_FORBIDDEN)
            )
            .authenticationEntryPoint(
                (request, response, accessDeniedException)
                    -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
            )
        );

        // REST API의 URI에 대한 인가 적용/미적용 설정
        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
            .requestMatchers(
                Arrays.stream(ALLOWED_PATHS)
                    .map(AntPathRequestMatcher::new)
                    .toArray(RequestMatcher[]::new)
            ).permitAll()
            .requestMatchers(
                new AntPathRequestMatcher("/v1/user", HttpMethod.POST.name())
            ).permitAll()
            .anyRequest().authenticated()
        );

        http.addFilterBefore(
            jwtFilter,
            UsernamePasswordAuthenticationFilter.class
        );

        http.oauth2Login(oauth2 -> oauth2
            .userInfoEndpoint(
                userInfoEndpoint -> userInfoEndpoint.userService(principalOauth2UserService))
            .successHandler(oAuth2LoginSuccessHandler)
        );
        //사용자 프로필 정보를 가져오고 그 정보를 토대로 회원가입을 자동으로 진행
        //정보가 추가적으로 필요하면 추가적으로 요구받아야함
        //OAuth 완료가 되면 엑세스토큰 + 사용자 프로필 정보를 한번에 받음 로그인 성공시 oAuth2LoginSuccessHandler에서 처리해서 jwt토큰 response

        return http.build();
    }
}
