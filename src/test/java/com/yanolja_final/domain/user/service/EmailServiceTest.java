package com.yanolja_final.domain.user.service;

import java.util.HashMap;
import java.util.Map;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("이메일 인증 코드 발송에 성공한다")
    void sendVerificationEmail_Success() throws Exception {
        // Arrange
        String email = "test@example.com";
        JavaMailSenderImpl mockMailSender = mock(JavaMailSenderImpl.class);
        MimeMessage mockMimeMessage = new MimeMessage((Session) null);
        when(mockMailSender.createMimeMessage()).thenReturn(mockMimeMessage);

        // Inject
        ReflectionTestUtils.setField(emailService, "javaMailSender", mockMailSender);

        // Act
        String code = emailService.sendVerificationEmail(email);

        // Assert
        assertNotNull(code);
        verify(mockMailSender).send(any(MimeMessage.class));
    }

    @Test
    @DisplayName("인증 코드 확인이 성공한다")
    void verifyEmailCode_Success() {
        // Arrange
        String email = "test@example.com";
        String authCode = "12345678";

        // Inject
        Map<String, String> mockVerificationCodes = new HashMap<>();
        mockVerificationCodes.put(email, authCode);
        ReflectionTestUtils.setField(emailService, "verificationCodes", mockVerificationCodes);

        // Act
        boolean result = emailService.verifyEmailCode(email, authCode);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("잘못된 인증 코드로 인증 실패한다")
    void verifyEmailCode_Failure() {
        // Arrange
        String email = "test@example.com";
        String code = "incorrect";

        // Act
        boolean result = emailService.verifyEmailCode(email, code);

        // Assert
        assertFalse(result);
    }
}