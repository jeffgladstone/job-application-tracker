package com.example.tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_applications")
@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String role;
    private String location;
    private Integer salaryExpectation;
    private LocalDate dateApplied;
    private String status;

    @Column(length = 2000)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
