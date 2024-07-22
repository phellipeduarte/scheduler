package com.scheduler.dtos;

import com.scheduler.models.Appointment;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentResponseDTO(UUID uuid, LocalDateTime start, LocalDateTime end, UUID attendantId, UUID clientId, Integer jobId) {

    public AppointmentResponseDTO(Appointment appointment) {
        this(appointment.getUuid(), appointment.getStart(), appointment.getEndTime(), appointment.getAttendant().getUuid(), appointment.getClient().getUuid(), appointment.getJob().getId());
    }
}
