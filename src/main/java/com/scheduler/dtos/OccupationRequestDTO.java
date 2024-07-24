package com.scheduler.dtos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

public record OccupationRequestDTO(DayOfWeek weekDay, LocalTime startTime, LocalTime endTime, UUID attendantId) {
}
