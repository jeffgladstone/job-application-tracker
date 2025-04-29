package com.example.tracker.service;

import com.example.tracker.model.JobApplication;
import com.example.tracker.model.User;
import com.example.tracker.repository.JobApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    @Cacheable("applications")
    public List<JobApplication> getAll() {
        log.info("Fetching all job applications");
        User currentUser = getCurrentUser();
        return jobApplicationRepository.findByUser(currentUser);
    }

    public Optional<JobApplication> getById(Long id) {
        log.info("Fetching job application with ID: {}", id);
        User currentUser = getCurrentUser();
        return jobApplicationRepository.findByIdAndUser(id, currentUser);
    }

    @CacheEvict(value = "applications", allEntries = true)
    public JobApplication save(JobApplication jobApplication) {
        if (jobApplication.getId() == null) {
            log.info("Creating new job application: {}", jobApplication);
        } else {
            log.info("Updating job application with ID {}: {}", jobApplication.getId(), jobApplication);
        }
        User currentUser = getCurrentUser();
        jobApplication.setUser(currentUser);
        return jobApplicationRepository.save(jobApplication);
    }

    @CacheEvict(value = "applications", allEntries = true)
    public void delete(Long id) {
        log.warn("Deleting job application with ID: {}", id);
        User currentUser = getCurrentUser();
        jobApplicationRepository.findByIdAndUser(id, currentUser)
                .ifPresent(jobApplicationRepository::delete);
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
