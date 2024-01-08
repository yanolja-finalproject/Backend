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
        "/v1/docs/**", "/v1/users/email/**", "/h2-console/**", "/v1/themes/**"
    };

    private final JwtFilter jwtFilter;

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

        return http.build();
    }
}
