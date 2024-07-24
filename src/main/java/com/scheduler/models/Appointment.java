package com.scheduler.models;


import com.scheduler.dtos.AppointmentRequestDTO;
import com.scheduler.enums.AppointmentStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @DateTimeFormat(pattern = "yyyy-MM-ddThh:MM")
    private LocalDateTime start;

    @DateTimeFormat(pattern = "yyyy-MM-ddThh:MM")
    private LocalDateTime endTime;

    private AppointmentStatusEnum appointmentStatus;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "attendant_id", nullable = false)
    private Attendant attendant;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    public Appointment(AppointmentRequestDTO appointmentRequestDTO, Client client, Attendant attendant, Job job) {
        this.start = appointmentRequestDTO.start();
        this.endTime = this.start.plusMinutes(job.getDurationMinutes().longValue());
        this.client = client;
        this.attendant = attendant;
        this.appointmentStatus = AppointmentStatusEnum.AGENDADO;
        this.job = job;
    }

    public void uncheck(){
        this.appointmentStatus = AppointmentStatusEnum.DESMARCADO;
    }

    public void cancel(){
        this.appointmentStatus = AppointmentStatusEnum.CANCELADO;
    }
}
