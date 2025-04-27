package com.example.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
