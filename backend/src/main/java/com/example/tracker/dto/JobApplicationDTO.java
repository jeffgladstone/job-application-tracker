package com.example.tracker.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class JobApplicationDTO {
    private Long id;
    private String companyName;
    private String role;
    private LocalDate dateApplied;
    private String status;
    private String notes;
    private String location;
    private Integer salaryExpectation;
}
