package com.example.tracker.service;

import com.example.tracker.model.JobApplication;
import com.example.tracker.repository.JobApplicationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
    private JobApplicationRepository repository;

    @Test
    void shouldFetchAllApplications() {
        // given
        when(repository.findAll()).thenReturn(List.of(new JobApplication(), new JobApplication()));

        // when
        List<JobApplication> result = service.getAll();

        // then
        assertThat(result).hasSize(2);
        verify(repository).findAll();
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
        verify(repository).save(captor.capture());

        JobApplication saved = captor.getValue();
        assertThat(saved.getCompanyName()).isEqualTo("OpenAI");
    }

    @Test
    void shouldReturnApplicationById() {
        // given
        JobApplication app = new JobApplication();
        app.setId(42L);
        when(repository.findById(42L)).thenReturn(Optional.of(app));

        // when
        Optional<JobApplication> result = service.getById(42L);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(42L);
    }

    @Test
    void shouldDeleteApplication() {
        // when
        service.delete(10L);

        // then
        verify(repository, times(1)).deleteById(10L);
    }
}
