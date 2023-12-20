package com.yanolja_final.domain.auth.service;

import com.yanolja_final.domain.auth.dto.TokenDTO;
import com.yanolja_final.domain.auth.exception.InvalidPasswordException;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.global.config.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public TokenDTO login(User user, String password) {
        if (!passwordEncoder.matches(password, user.getEncryptedPassword())) {
            throw new InvalidPasswordException();
        }
        String accessToken = jwtProvider.createToken(user);
        return new TokenDTO(accessToken);
    }
}
