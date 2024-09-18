package com.scheduler.dtos;

import com.scheduler.models.Client;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ClientResponseDTO(UUID uuid, String name, String phone, List<AppointmentResponseClientDTO> appointments) {

    public ClientResponseDTO(Client client) {
        this(client.getUuid(), client.getName(), client.getPhone(), client.getAppointments().stream().filter(appointment -> appointment.getStart().isAfter(LocalDateTime.now())).map(AppointmentResponseClientDTO::new).toList());
    }
}
