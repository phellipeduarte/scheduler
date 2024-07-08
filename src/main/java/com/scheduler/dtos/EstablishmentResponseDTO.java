package com.scheduler.dtos;

import com.scheduler.models.Establishment;

public record EstablishmentResponseDTO(Integer id, String name){

    public EstablishmentResponseDTO(Establishment establishment) {
        this(establishment.getId(), establishment.getName());
    }
}
