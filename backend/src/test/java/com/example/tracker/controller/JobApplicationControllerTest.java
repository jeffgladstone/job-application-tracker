package com.example.tracker.controller;

import com.example.tracker.dto.JobApplicationDTO;
import com.example.tracker.mapper.JobApplicationMapper;
import com.example.tracker.model.JobApplication;
import com.example.tracker.service.JobApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobApplicationController.class)
class JobApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobApplicationService service;

    @MockBean
    private JobApplicationMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllApplications() throws Exception {
        JobApplication entity = createSampleJobApplication();
        JobApplicationDTO dto = createSampleJobApplicationDTO();

        when(service.getAll()).thenReturn(List.of(entity));
        when(mapper.toDtoList(List.of(entity))).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/applications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].companyName").value("Google"));
    }

    @Test
    void shouldReturnApplicationById() throws Exception {
        JobApplication entity = createSampleJobApplication();
        JobApplicationDTO dto = createSampleJobApplicationDTO();

        when(service.getById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        mockMvc.perform(get("/api/applications/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("Google"));
    }

    @Test
    void shouldCreateNewApplication() throws Exception {
        JobApplicationDTO dto = createSampleJobApplicationDTO();
        JobApplication entity = createSampleJobApplication();

        when(service.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);
        when(mapper.toEntity(Mockito.any(JobApplicationDTO.class))).thenReturn(entity);

        mockMvc.perform(post("/api/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("Google"));
    }

    @Test
    void shouldDeleteApplication() throws Exception {
        mockMvc.perform(delete("/api/applications/5"))
                .andExpect(status().isOk());

        Mockito.verify(service).delete(5L);
    }

    private JobApplication createSampleJobApplication() {
        return JobApplication.builder()
                .id(1L)
                .companyName("Google")
                .role("Engineer")
                .status("Applied")
                .dateApplied(LocalDate.of(2025, 4, 25))
                .notes("Cool role")
                .location("Remote")
                .salaryExpectation(150000)
                .build();
    }

    private JobApplicationDTO createSampleJobApplicationDTO() {
        return JobApplicationDTO.builder()
                .id(1L)
                .companyName("Google")
                .role("Engineer")
                .status("Applied")
                .dateApplied(LocalDate.of(2025, 4, 25))
                .notes("Cool role")
                .location("Remote")
                .salaryExpectation(150000)
                .build();
    }
}
