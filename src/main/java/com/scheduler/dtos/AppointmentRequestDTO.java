package com.scheduler.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentRequestDTO(LocalDateTime start, UUID clientId, UUID attendantId, Integer jobId){
}
