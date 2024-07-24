package com.scheduler.models;

import com.scheduler.dtos.AttendantRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table
@Entity
@Getter
@Setter
public class Attendant extends Person{

    private String title;

    @Column(length = 800)
    private String description;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "establishment_id", nullable = false)
    private Establishment establishment;

    @OneToMany(mappedBy = "attendant")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "attendant")
    private List<Occupation> occupations;

    public Attendant(AttendantRequestDTO attendantRequestDTO, Establishment establishment) {
        super(attendantRequestDTO.name());
        this.title = attendantRequestDTO.title();
        this.description = attendantRequestDTO.description();
        this.imageUrl = attendantRequestDTO.imageUrl();
        this.establishment = establishment;
    }

    public Attendant() {
        super();
    }
}
