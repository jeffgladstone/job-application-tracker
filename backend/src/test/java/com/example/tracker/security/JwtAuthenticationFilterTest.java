package com.example.tracker.security;

import com.example.tracker.model.User;
import com.example.tracker.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldBypassPublicUrls() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/api/auth/login");

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    void shouldSkipWhenNoAuthorizationHeader() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/api/protected/resource");
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    void shouldSkipWhenInvalidToken() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/api/protected/resource");
        when(request.getHeader("Authorization")).thenReturn("Bearer invalidToken");
        when(jwtUtil.extractEmail("invalidToken")).thenThrow(new RuntimeException("Bad token"));

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    void shouldAuthenticateValidToken() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/api/protected/resource");
        when(request.getHeader("Authorization")).thenReturn("Bearer validToken");
        when(jwtUtil.extractEmail("validToken")).thenReturn("test@example.com");

        User user = new User();
        user.setEmail("test@example.com");

        when(userService.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(jwtUtil.isTokenValid("validToken")).thenReturn(true);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNotNull();
        assertThat(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).isEqualTo(user);
    }

    @Test
    void shouldSkipWhenUserNotFound() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/api/protected/resource");
        when(request.getHeader("Authorization")).thenReturn("Bearer validToken");
        when(jwtUtil.extractEmail("validToken")).thenReturn("missing@example.com");

        when(userService.findByEmail("missing@example.com")).thenReturn(Optional.empty());

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }
}
