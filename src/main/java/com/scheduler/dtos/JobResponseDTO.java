package com.scheduler.dtos;

import com.scheduler.models.Job;

public record JobResponseDTO(Integer id, String name, Double price, Integer durationMinutes, Integer establishmentId) {

    public JobResponseDTO(Job job) {
        this(job.getId(), job.getName(), job.getPrice(), job.getDurationMinutes(), job.getEstablishment().getId());
    }
}
