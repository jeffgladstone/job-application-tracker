package com.example.tracker.controller;

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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobApplicationController.class)
class JobApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobApplicationService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllApplications() throws Exception {
        JobApplication app1 = new JobApplication();
        app1.setCompanyName("OpenAI");

        JobApplication app2 = new JobApplication();
        app2.setCompanyName("Google");

        when(service.getAll()).thenReturn(List.of(app1, app2));

        mockMvc.perform(get("/api/applications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].companyName").value("OpenAI"));
    }

    @Test
    void shouldReturnApplicationById() throws Exception {
        JobApplication app = new JobApplication();
        app.setId(1L);
        app.setCompanyName("Netflix");

        when(service.getById(1L)).thenReturn(Optional.of(app));

        mockMvc.perform(get("/api/applications/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("Netflix"));
    }

    @Test
    void shouldCreateNewApplication() throws Exception {
        JobApplication app = new JobApplication();
        app.setCompanyName("Amazon");
        app.setRole("SWE");
        app.setStatus("Applied");
        app.setDateApplied(LocalDate.now());

        when(service.save(any(JobApplication.class))).thenReturn(app);

        mockMvc.perform(post("/api/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(app)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("Amazon"));
    }

    @Test
    void shouldDeleteApplication() throws Exception {
        mockMvc.perform(delete("/api/applications/5"))
                .andExpect(status().isOk());

        Mockito.verify(service).delete(5L);
    }
}
