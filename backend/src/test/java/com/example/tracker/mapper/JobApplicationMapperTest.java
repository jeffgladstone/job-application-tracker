package com.example.tracker.mapper;

import com.example.tracker.dto.JobApplicationDTO;
import com.example.tracker.model.JobApplication;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JobApplicationMapperTest {

    private final JobApplicationMapper mapper = Mappers.getMapper(JobApplicationMapper.class);

    @Test
    void shouldMapEntityToDto() {
        JobApplication entity = JobApplication.builder()
                .id(1L)
                .companyName("Meta")
                .role("Engineer")
                .dateApplied(LocalDate.of(2025, 4, 22))
                .status("Applied")
                .notes("Exciting role!")
                .location("Remote")
                .salaryExpectation(150000)
                .build();

        JobApplicationDTO dto = mapper.toDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getCompanyName()).isEqualTo("Meta");
        assertThat(dto.getRole()).isEqualTo("Engineer");
        assertThat(dto.getDateApplied()).isEqualTo(LocalDate.of(2025, 4, 22));
        assertThat(dto.getStatus()).isEqualTo("Applied");
        assertThat(dto.getNotes()).isEqualTo("Exciting role!");
        assertThat(dto.getLocation()).isEqualTo("Remote");
        assertThat(dto.getSalaryExpectation()).isEqualTo(150000);
    }

    @Test
    void shouldMapDtoToEntity() {
        JobApplicationDTO dto = JobApplicationDTO.builder()
                .id(2L)
                .companyName("Supercell")
                .role("Game Developer")
                .dateApplied(LocalDate.of(2025, 5, 1))
                .status("Interviewing")
                .notes("Big fan of their games")
                .location("Helsinki")
                .salaryExpectation(140000)
                .build();

        JobApplication entity = mapper.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(2L);
        assertThat(entity.getCompanyName()).isEqualTo("Supercell");
        assertThat(entity.getRole()).isEqualTo("Game Developer");
        assertThat(entity.getDateApplied()).isEqualTo(LocalDate.of(2025, 5, 1));
        assertThat(entity.getStatus()).isEqualTo("Interviewing");
        assertThat(entity.getNotes()).isEqualTo("Big fan of their games");
        assertThat(entity.getLocation()).isEqualTo("Helsinki");
        assertThat(entity.getSalaryExpectation()).isEqualTo(140000);
    }

    @Test
    void shouldMapEntityListToDtoList() {
        JobApplication entity = JobApplication.builder()
                .id(3L)
                .companyName("Google")
                .role("Software Engineer")
                .build();

        List<JobApplicationDTO> dtoList = mapper.toDtoList(Collections.singletonList(entity));

        assertThat(dtoList).hasSize(1);
        assertThat(dtoList.get(0).getCompanyName()).isEqualTo("Google");
    }

    @Test
    void shouldMapDtoListToEntityList() {
        JobApplicationDTO dto = JobApplicationDTO.builder()
                .id(4L)
                .companyName("Amazon")
                .role("Backend Developer")
                .build();

        List<JobApplication> entityList = mapper.toEntityList(Collections.singletonList(dto));

        assertThat(entityList).hasSize(1);
        assertThat(entityList.get(0).getCompanyName()).isEqualTo("Amazon");
    }
}