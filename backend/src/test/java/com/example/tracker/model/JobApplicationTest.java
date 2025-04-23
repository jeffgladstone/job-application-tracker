package com.example.tracker.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class JobApplicationTest {

    @Test
    void shouldSetAndGetFields() {
        JobApplication job = new JobApplication();
        job.setId(1L);
        job.setCompanyName("OpenAI");
        job.setRole("Engineer");
        job.setDateApplied(LocalDate.of(2025, 4, 22));
        job.setStatus("Applied");
        job.setNotes("Super cool position");

        assertThat(job.getId()).isEqualTo(1L);
        assertThat(job.getCompanyName()).isEqualTo("OpenAI");
        assertThat(job.getRole()).isEqualTo("Engineer");
        assertThat(job.getDateApplied()).isEqualTo(LocalDate.of(2025, 4, 22));
        assertThat(job.getStatus()).isEqualTo("Applied");
        assertThat(job.getNotes()).isEqualTo("Super cool position");
    }

    @Test
    void shouldBuildJobApplicationUsingBuilder() {
        JobApplication job = JobApplication.builder()
                .id(100L)
                .companyName("Supercell")
                .role("Game Engineer")
                .status("Applied")
                .notes("Really excited about this one")
                .dateApplied(LocalDate.of(2025, 4, 22))
                .build();

        assertThat(job.getId()).isEqualTo(100L);
        assertThat(job.getCompanyName()).isEqualTo("Supercell");
        assertThat(job.getRole()).isEqualTo("Game Engineer");
        assertThat(job.getStatus()).isEqualTo("Applied");
        assertThat(job.getNotes()).isEqualTo("Really excited about this one");
        assertThat(job.getDateApplied()).isEqualTo(LocalDate.of(2025, 4, 22));
    }
}
