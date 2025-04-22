package com.example.tracker.service;

import com.example.tracker.model.JobApplication;
import com.example.tracker.repository.JobApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    public List<JobApplication> getAll() {
        log.info("Fetching all job applications");
        return repository.findAll();
    }

    public Optional<JobApplication> getById(Long id) {
        log.info("Fetching job application with ID: {}", id);
        return repository.findById(id);
    }

    public JobApplication save(JobApplication jobApplication) {
        if (jobApplication.getId() == null) {
            log.info("Creating new job application: {}", jobApplication);
        } else {
            log.info("Updating job application with ID {}: {}", jobApplication.getId(), jobApplication);
        }
        return repository.save(jobApplication);
    }

    public void delete(Long id) {
        log.warn("Deleting job application with ID: {}", id);
        repository.deleteById(id);
    }
}
