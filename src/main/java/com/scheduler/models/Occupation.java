package com.scheduler.models;

import com.scheduler.dtos.OccupationRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Occupation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private DayOfWeek weekDay;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    private Attendant attendant;

    public Occupation(OccupationRequestDTO occupationRequestDTO, Attendant attendant) {
        this.weekDay = occupationRequestDTO.weekDay();
        this.startTime = occupationRequestDTO.startTime();
        this.endTime = occupationRequestDTO.endTime();
        this.attendant = attendant;
    }
}
