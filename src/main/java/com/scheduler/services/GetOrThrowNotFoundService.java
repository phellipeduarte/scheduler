package com.scheduler.services;

import com.scheduler.exceptions.*;
import com.scheduler.models.*;
import com.scheduler.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetOrThrowNotFoundService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired
    AttendantRepository attendantRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    OccupationRepository occupationRepository;


    public Client getClientOrThrowClientNotFound(UUID clientId){
        Optional<Client> clientOptional = clientRepository.findByUuid(clientId);
        if (clientOptional.isEmpty()){
            throw new ClientNotFoundException();
        }
        return clientOptional.get();
    }


    public Job getJobOrThrowJobNotFound(Integer jobId){
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if (jobOptional.isEmpty()){
            throw new JobNotFoundException();
        }
        return jobOptional.get();
    }

    public Establishment getEstablishmentOrThrowEstablishmentNotFound(Integer establishmentId){
        Optional<Establishment> establishmentOptional = establishmentRepository.findById(establishmentId);
        if (establishmentOptional.isEmpty()){
            throw new EstablishmentNotFoundException();
        }
        return establishmentOptional.get();
    }

    public Attendant getAttendantOrThrowAttendantNotFound(UUID attendantId){
        Optional<Attendant> attendantOptional = attendantRepository.findByUuid(attendantId);
        if (attendantOptional.isEmpty()){
            throw new AttendantNotFoundException();
        }
        return attendantOptional.get();
    }

    public Appointment getAppointmentOrThrowAppointmentNotFound(UUID appointmentId){
        Optional<Appointment> appointmentOptional = appointmentRepository.findByUuid(appointmentId);
        if (appointmentOptional.isEmpty()){
            throw new AppointmentNotFoundException();
        }
        return appointmentOptional.get();
    }

    public Occupation getOccupationOrThrowOccupationNotFound(Integer occupationId){
        Optional<Occupation> occupationOptional = occupationRepository.findById(occupationId);
        if(occupationOptional.isEmpty()){
            throw new OccupationNotFoundException();
        }
        return occupationOptional.get();
    }
}
