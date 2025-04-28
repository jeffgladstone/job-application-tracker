package com.example.tracker.service;

import com.example.tracker.model.JobApplication;
import com.example.tracker.model.User;
import com.example.tracker.repository.JobApplicationRepository;
import com.example.tracker.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class JobApplicationServiceTest {

    @Autowired
    private JobApplicationService service;

    @MockBean
    private JobApplicationRepository jobApplicationRepository;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUpSecurityContext() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("password");
        mockUser.setName("Test User");

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(mockUser, null, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void shouldFetchAllApplications() {
        // given
        when(jobApplicationRepository.findByUser(any(User.class))).thenReturn(List.of(new JobApplication(), new JobApplication()));

        // when
        List<JobApplication> result = service.getAll();

        // then
        assertThat(result).hasSize(2);
        verify(jobApplicationRepository).findByUser(any(User.class));
    }

    @Test
    void shouldSaveApplication() {
        // given
        JobApplication jobApp = new JobApplication();
        jobApp.setCompanyName("OpenAI");
        jobApp.setRole("Engineer");
        jobApp.setDateApplied(LocalDate.now());
        jobApp.setStatus("Applied");

        // when
        service.save(jobApp);

        // then
        ArgumentCaptor<JobApplication> captor = ArgumentCaptor.forClass(JobApplication.class);
        verify(jobApplicationRepository).save(captor.capture());

        JobApplication saved = captor.getValue();
        assertThat(saved.getCompanyName()).isEqualTo("OpenAI");
    }

    @Test
    void shouldReturnApplicationById() {
        // given
        JobApplication app = new JobApplication();
        app.setId(42L);
        when(jobApplicationRepository.findByIdAndUser(eq(42L), any(User.class))).thenReturn(Optional.of(app));

        // when
        Optional<JobApplication> result = service.getById(42L);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(42L);
    }

    @Test
    void shouldDeleteApplication() {
        //given
        JobApplication app = new JobApplication();
        app.setId(10L);
        when(jobApplicationRepository.findByIdAndUser(eq(10L), any(User.class))).thenReturn(Optional.of(app));

        // when
        service.delete(10L);

        // then
        verify(jobApplicationRepository, times(1)).delete(app);
        verify(jobApplicationRepository, times(1)).findByIdAndUser(eq(10L), any(User.class));
    }

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

}
