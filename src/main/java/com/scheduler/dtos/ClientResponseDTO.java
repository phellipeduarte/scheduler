package com.scheduler.dtos;

import com.scheduler.models.Client;

import java.util.UUID;

public record ClientResponseDTO(UUID uuid, String name, String phone) {

    public ClientResponseDTO(Client client) {
        this(client.getUuid(), client.getName(), client.getPhone());
    }
}
