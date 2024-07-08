package com.scheduler.models;

import com.scheduler.dtos.EstablishmentRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Establishment(EstablishmentRequestDTO establishmentRequestDTO) {
        this.name = establishmentRequestDTO.name();
    }
}
