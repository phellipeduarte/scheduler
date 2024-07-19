package com.scheduler.services;

import com.scheduler.exceptions.AttendantNotFoundException;
import com.scheduler.exceptions.EstablishmentNotFoundException;
import com.scheduler.exceptions.JobNotFoundException;
import com.scheduler.models.Attendant;
import com.scheduler.models.Establishment;
import com.scheduler.models.Job;
import com.scheduler.repositories.AttendantRepository;
import com.scheduler.repositories.EstablishmentRepository;
import com.scheduler.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetOrThrowNotFoundService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired
    AttendantRepository attendantRepository;


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
}
