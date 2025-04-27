package com.example.tracker.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CorsConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCorsConfiguration() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.options("/api/applications")
                                .header("Origin", "https://job-application-tracker-1-15jv.onrender.com")
                                .header("Access-Control-Request-Method", "GET")
                )
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "https://job-application-tracker-1-15jv.onrender.com"))
                .andExpect(header().exists("Access-Control-Allow-Methods"))
                .andExpect(header().string("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE"));
    }
}
