package com.scheduler.models;

import com.scheduler.dtos.AttendantRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
