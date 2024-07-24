package com.scheduler.dtos;

import com.scheduler.models.Occupation;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record OccupationResponseDTO(Integer id, DayOfWeek weekDay, LocalTime startTime, LocalTime endTime) {

    public OccupationResponseDTO(Occupation occupation) {
        this(occupation.getId(), occupation.getWeekDay(), occupation.getStartTime(), occupation.getEndTime());
    }
}
