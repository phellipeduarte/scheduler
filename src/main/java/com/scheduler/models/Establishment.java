package com.scheduler.models;

import com.scheduler.dtos.EstablishmentRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "establishment")
    private List<Job> jobs;

    @OneToMany(mappedBy = "establishment")
    private List<Attendant> attendants;

    public Establishment(EstablishmentRequestDTO establishmentRequestDTO) {
        this.name = establishmentRequestDTO.name();
    }
}
