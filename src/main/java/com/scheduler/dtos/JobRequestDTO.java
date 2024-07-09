package com.scheduler.dtos;

public record JobRequestDTO(String name, Double price, Integer durationMinutes, Integer establishmentId) {
}
