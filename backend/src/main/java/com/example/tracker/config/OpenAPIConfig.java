package com.example.tracker.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI jobTrackerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Job Application Tracker API")
                        .version("1.0")
                        .description("API documentation for your job tracker app"));
    }
}
