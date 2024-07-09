package com.scheduler.services;

import com.scheduler.dtos.ClientResponseDTO;
import com.scheduler.dtos.JobRequestDTO;
import com.scheduler.dtos.JobResponseDTO;
import com.scheduler.exceptions.EstablishmentNotFoundException;
import com.scheduler.exceptions.JobNotFoundException;
import com.scheduler.models.Establishment;
import com.scheduler.models.Job;
import com.scheduler.repositories.EstablishmentRepository;
import com.scheduler.repositories.JobRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired
    JobRepository jobRepository;

    public List<JobResponseDTO> getJobs(Integer establishmentId) throws EstablishmentNotFoundException {
        Establishment establishment = getEstablishmentOrThrowEstablishmentNotFound(establishmentId);
        List<JobResponseDTO> jobs = establishment.getJobs().stream().map(JobResponseDTO::new).toList();
        return jobs;
    }

    public JobResponseDTO getJob(Integer id) throws JobNotFoundException {
        JobResponseDTO job = new JobResponseDTO(getJobOrThrowJobNotFound(id));
        return job;
    }

    public List<JobResponseDTO> saveJob(JobRequestDTO jobRequestDTO) throws EstablishmentNotFoundException {
        Establishment establishment = getEstablishmentOrThrowEstablishmentNotFound(jobRequestDTO.establishmentId());
        Job newJob = new Job(jobRequestDTO, establishment);
        jobRepository.save(newJob);
        List<JobResponseDTO> updatedJobs = establishment.getJobs().stream().map(JobResponseDTO::new).toList();
        return updatedJobs;
    }

    public JobResponseDTO updateJob(Integer id, JobRequestDTO jobRequestDTO) {
        Job job = getJobOrThrowJobNotFound(id);
        BeanUtils.copyProperties(jobRequestDTO, job);
        jobRepository.save(job);
        JobResponseDTO updatedJob = new JobResponseDTO(job);
        return updatedJob;
    }

    public void deleteJob(Integer id) {
        Job job = getJobOrThrowJobNotFound(id);
        jobRepository.delete(job);
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
}
