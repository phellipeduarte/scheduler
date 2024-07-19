package com.scheduler.dtos;

import com.scheduler.models.Attendant;

import java.util.UUID;

public record AttendantResponseDTO (UUID uuid, String name, String title, String description, String imageUrl, Integer establishmentId){

    public AttendantResponseDTO(Attendant attendant) {
        this(attendant.getUuid(), attendant.getName(), attendant.getTitle(), attendant.getDescription(), attendant.getImageUrl(), attendant.getEstablishment().getId());
    }
}
