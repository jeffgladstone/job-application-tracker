package com.example.tracker.repository;

import com.example.tracker.model.JobApplication;
import com.example.tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUser(User user);
    Optional<JobApplication> findByIdAndUser(Long id, User user);
}
