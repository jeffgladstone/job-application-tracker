package com.example.tracker.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    void shouldGenerateAndValidateToken() {
        String email = "test@example.com";

        String token = jwtUtil.generateToken(email);

        assertThat(token).isNotBlank();
        assertThat(jwtUtil.isTokenValid(token)).isTrue();
        assertThat(jwtUtil.extractEmail(token)).isEqualTo(email);
    }

    @Test
    void shouldDetectInvalidToken() {
        String invalidToken = "invalid.token.here";

        boolean isValid = jwtUtil.isTokenValid(invalidToken);

        assertThat(isValid).isFalse();
    }
}