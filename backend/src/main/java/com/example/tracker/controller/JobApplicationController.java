package com.example.tracker.controller;

import com.example.tracker.dto.JobApplicationDTO;
import com.example.tracker.mapper.JobApplicationMapper;
import com.example.tracker.model.JobApplication;
import com.example.tracker.service.JobApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:3000") // React frontend
public class JobApplicationController {

    private final JobApplicationService service;
    private final JobApplicationMapper mapper;

    public JobApplicationController(JobApplicationService service, JobApplicationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<JobApplicationDTO> getAll() {
        List<JobApplication> applications = service.getAll();
        return mapper.toDtoList(applications);
    }

    @GetMapping("/{id}")
    public JobApplicationDTO getById(@PathVariable("id") Long id) {
        JobApplication application = service.getById(id).orElseThrow();
        return mapper.toDto(application);
    }

    @PostMapping
    public JobApplicationDTO create(@RequestBody JobApplicationDTO jobAppDTO) {
        JobApplication application = service.save(mapper.toEntity(jobAppDTO));
        return mapper.toDto(application);
    }

    @PutMapping("/{id}")
    public JobApplicationDTO update(@PathVariable("id") Long id, @RequestBody JobApplicationDTO updatedAppDTO) {
        updatedAppDTO.setId(id);
        JobApplication application = service.save(mapper.toEntity(updatedAppDTO));
        return mapper.toDto(application);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
