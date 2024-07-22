package com.scheduler.models;

import com.scheduler.dtos.ClientRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "client")
@Entity(name = "client")
@Getter
@Setter
public class Client extends Person{
    private String phone;

    @OneToMany(mappedBy = "client")
    private List<Appointment> appointments;

    public Client(ClientRequestDTO clientRequestDTO) {
        super(clientRequestDTO.name());
        this.phone = clientRequestDTO.phone();
    }

    public Client() {
        super();
    }
}
