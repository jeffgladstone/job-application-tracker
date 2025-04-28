package com.example.tracker.controller;

import com.example.tracker.dto.JobApplicationDTO;
import com.example.tracker.mapper.JobApplicationMapper;
import com.example.tracker.model.JobApplication;
import com.example.tracker.service.JobApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = JobApplicationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {JobApplicationController.class, JobApplicationControllerTest.TestSecurityConfig.class})
class JobApplicationControllerTest {

    @Configuration
    static class TestSecurityConfig {
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
            return http.build();
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobApplicationService service;

    @MockBean
    private JobApplicationMapper mapper;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeEach
    void setUp() {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken("testUser", null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

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
        JobApplication entity = createSampleJobApplication();
        JobApplicationDTO dto = createSampleJobApplicationDTO();

        when(mapper.toEntity(Mockito.any(JobApplicationDTO.class))).thenReturn(entity);
        when(service.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        mockMvc.perform(post("/api/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("Google"));
    }

    @Test
    void shouldDeleteApplication() throws Exception {
        doNothing().when(service).delete(5L);

        mockMvc.perform(delete("/api/applications/5"))
                .andExpect(status().isOk());

        Mockito.verify(service).delete(5L);
    }

    private JobApplication createSampleJobApplication() {
        JobApplication job = new JobApplication();
        job.setId(1L);
        job.setCompanyName("Google");
        job.setRole("Engineer");
        job.setStatus("Applied");
        job.setDateApplied(LocalDate.of(2025, 4, 25));
        job.setNotes("Cool role");
        job.setLocation("Remote");
        job.setSalaryExpectation(150000);
        return job;
    }

    private JobApplicationDTO createSampleJobApplicationDTO() {
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setId(1L);
        dto.setCompanyName("Google");
        dto.setRole("Engineer");
        dto.setStatus("Applied");
        dto.setDateApplied(LocalDate.of(2025, 4, 25));
        dto.setNotes("Cool role");
        dto.setLocation("Remote");
        dto.setSalaryExpectation(150000);
        return dto;
    }
}
