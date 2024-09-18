package com.scheduler.dtos;

import com.scheduler.enums.AppointmentStatusEnum;
import com.scheduler.models.Appointment;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentResponseClientDTO(UUID uuid, LocalDateTime start, LocalDateTime end, AppointmentStatusEnum appointmentStatus, UUID attendantId, Integer jobId ) {

    public AppointmentResponseClientDTO(Appointment appointment) {
        this(appointment.getUuid(), appointment.getStart(), appointment.getEndTime(), appointment.getAppointmentStatus(), appointment.getAttendant().getUuid(), appointment.getJob().getId());
    }
}
