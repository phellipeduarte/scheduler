package com.scheduler.services;

import com.scheduler.dtos.AppointmentRequestDTO;
import com.scheduler.dtos.AppointmentResponseDTO;
import com.scheduler.enums.AppointmentStatusEnum;
import com.scheduler.exceptions.TimeNotAvailableException;
import com.scheduler.models.*;
import com.scheduler.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
        List<Appointment> appointmentList = attendant.getAppointments().stream().filter(appointment -> appointment.getStart().isAfter(LocalDateTime.now()) && appointment.getAppointmentStatus().equals(AppointmentStatusEnum.AGENDADO)).toList();
        Appointment appointment = new Appointment(appointmentRequestDTO, client, attendant, job);

        if (!appointment.getStart().isAfter(LocalDateTime.now())){
            throw new TimeNotAvailableException();
        }

        if(!isAppointmentInBusinessOccupation(appointment.getStart().toLocalTime())){
            throw new TimeNotAvailableException();
        }

        if(!isAppointmentInAttendantOccupation(attendant, appointment)){
            throw new TimeNotAvailableException();
        }

        if (!isAttendantAvailable(appointmentList, appointment)){
            throw new TimeNotAvailableException();
        }

        appointmentRepository.save(appointment);
        return new AppointmentResponseDTO(appointment);
    }

    public AppointmentResponseDTO uncheckAppointment(UUID appointmentId) {
        Appointment appointment = GetOrThrowNotFoundService.getAppointmentOrThrowAppointmentNotFound(appointmentId);
        appointment.uncheck();
        appointmentRepository.save(appointment);
        return new AppointmentResponseDTO(appointment);
    }

    public AppointmentResponseDTO cancelAppointment(UUID appointmentId) {
        Appointment appointment = GetOrThrowNotFoundService.getAppointmentOrThrowAppointmentNotFound(appointmentId);
        appointment.cancel();
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

    public Boolean isAppointmentInAttendantOccupation(Attendant attendant, Appointment appointment){
        for(Occupation occupation : attendant.getOccupations()){
            if (occupation.getWeekDay().equals(appointment.getStart().getDayOfWeek())){
                if(!isTimeBetween(appointment.getStart().toLocalTime(), occupation.getStartTime(), occupation.getEndTime())){
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean isTimeBetween(LocalTime time, LocalTime start, LocalTime end){
        if (time.isBefore(start) || time.isAfter(end)){
            return false;
        }
        return true;
    }

    public Boolean isAppointmentInBusinessOccupation(LocalTime time){
        return isTimeBetween(time, LocalTime.parse("07:00"), LocalTime.parse("22:00"));
    }
}
