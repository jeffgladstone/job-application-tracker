package com.example.tracker.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void shouldSetAndGetFields() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("hashedPassword");
        user.setJobApplications(Collections.emptyList());

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("John Doe");
        assertThat(user.getEmail()).isEqualTo("john@example.com");
        assertThat(user.getPassword()).isEqualTo("hashedPassword");
        assertThat(user.getJobApplications()).isEmpty();
    }

    @Test
    void shouldBuildUserUsingBuilder() {
        User user = User.builder()
                .id(2L)
                .name("Jane Smith")
                .email("jane@example.com")
                .password("anotherHashedPassword")
                .jobApplications(Collections.emptyList())
                .build();

        assertThat(user.getId()).isEqualTo(2L);
        assertThat(user.getName()).isEqualTo("Jane Smith");
        assertThat(user.getEmail()).isEqualTo("jane@example.com");
        assertThat(user.getPassword()).isEqualTo("anotherHashedPassword");
        assertThat(user.getJobApplications()).isEmpty();
    }
}