package com.yanolja_final.global.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 오직 Bean 등록 하나만 하는 등의 단순 설정을 모아두는 클래스
 * (빈 등록이나 설정을 여러 개 해야 한다면 SecurityConfig 등으로 클래스를 따로 파는 것 권장)
 */
@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // RestController에서 json 응답 시 null 값의 필드는 아예 보여주지 않도록 설정하는 부분
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    // 임시 Security 설정
    @Bean
    public SecurityFilterChain temporarySecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .anyRequest().permitAll())
            .sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
