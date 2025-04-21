package com.example.tracker.service;

import com.example.tracker.model.JobApplication;
import com.example.tracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    public List<JobApplication> getAll() {
        return repository.findAll();
    }

    public Optional<JobApplication> getById(Long id) {
        return repository.findById(id);
    }

    public JobApplication save(JobApplication jobApplication) {
        return repository.save(jobApplication);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
