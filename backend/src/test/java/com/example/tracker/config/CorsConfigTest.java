package com.example.tracker.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "cors.allowed-origins=http://localhost:3000,https://job-application-tracker-1-15jv.onrender.com"
})
public class CorsConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCorsConfigurationForLocalhostAndRender() throws Exception {
        // Test for localhost
        mockMvc.perform(
                        MockMvcRequestBuilders.options("/api/applications")
                                .header("Origin", "http://localhost:3000")
                                .header("Access-Control-Request-Method", "GET")
                )
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:3000"))
                .andExpect(header().exists("Access-Control-Allow-Methods"))
                .andExpect(header().string("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE"));

        // Test for Render frontend
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
