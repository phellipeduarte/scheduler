package com.scheduler.services;

import com.scheduler.dtos.AppointmentRequestDTO;
import com.scheduler.dtos.AppointmentResponseDTO;
import com.scheduler.exceptions.TimeNotAvailableException;
import com.scheduler.models.Appointment;
import com.scheduler.models.Attendant;
import com.scheduler.models.Client;
import com.scheduler.models.Job;
import com.scheduler.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    GetOrThrowNotFoundService GetOrThrowNotFoundService;

    public AppointmentResponseDTO getAppointment(UUID uuid){
        return new AppointmentResponseDTO(GetOrThrowNotFoundService.getAppointmentOrThrowAppointmentNotFound(uuid));
    }

    public AppointmentResponseDTO saveAppointment(AppointmentRequestDTO appointmentRequestDTO){
        Client client = GetOrThrowNotFoundService.getClientOrThrowClientNotFound(appointmentRequestDTO.clientId());
        Attendant attendant = GetOrThrowNotFoundService.getAttendantOrThrowAttendantNotFound(appointmentRequestDTO.attendantId());
        Job job = GetOrThrowNotFoundService.getJobOrThrowJobNotFound(appointmentRequestDTO.jobId());
        List<Appointment> appointmentList = attendant.getAppointments().stream().filter(appointment -> appointment.getStart().isAfter(LocalDateTime.now())).toList();
        Appointment appointment = new Appointment(appointmentRequestDTO, client, attendant, job);

        if (!appointment.getStart().isAfter(LocalDateTime.now())){
            throw new TimeNotAvailableException();
        }

        if (!isAttendantAvailable(appointmentList, appointment)){
            throw new TimeNotAvailableException();
        }

        appointmentRepository.save(appointment);
        return new AppointmentResponseDTO(appointment);
    }

    public Boolean isAttendantAvailable(List<Appointment> appointments, Appointment newAppointment){
        for (Appointment appointment : appointments){
            if (newAppointment.getStart().isBefore(appointment.getStart())){
                if(newAppointment.getEndTime().isAfter(appointment.getStart())){
                    return false;
                }
            } else {
                if(newAppointment.getStart().isBefore(appointment.getEndTime())){
                    return false;
                }
            }
        }
        return true;
    }
}
