package com.yanolja_final.global.config.security;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private static final String[] WHITELIST_FOR_ALL_METHOD
        = {};

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

        // REST API의 URI에 대한 인가 적용/미적용 설정
        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
            .requestMatchers(WHITELIST_FOR_ALL_METHOD).permitAll()
            .requestMatchers(HttpMethod.POST.name(), "/v1/user").permitAll()
            .anyRequest().authenticated()
        );

        return http.build();
    }
}
