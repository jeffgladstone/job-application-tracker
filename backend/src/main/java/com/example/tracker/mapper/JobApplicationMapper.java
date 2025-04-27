package com.example.tracker.mapper;

import com.example.tracker.dto.JobApplicationDTO;
import com.example.tracker.model.JobApplication;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {

    JobApplicationDTO toDto(JobApplication entity);

    JobApplication toEntity(JobApplicationDTO dto);

    List<JobApplicationDTO> toDtoList(List<JobApplication> entities);

    List<JobApplication> toEntityList(List<JobApplicationDTO> dtos);
}
