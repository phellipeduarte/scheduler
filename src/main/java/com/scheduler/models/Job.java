package com.scheduler.models;

import com.scheduler.dtos.JobRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Double price;
    private Integer durationMinutes;

    @ManyToOne
    @JoinColumn(name = "establishment_id", nullable = false)
    private Establishment establishment;

    public Job(JobRequestDTO jobRequestDTO, Establishment establishment) {
        this.name = jobRequestDTO.name();
        this.price = jobRequestDTO.price();
        this.durationMinutes = jobRequestDTO.durationMinutes();
        this.establishment = establishment;
    }
}
