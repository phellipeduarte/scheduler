package com.scheduler.services;

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

@Service
public class JobService {

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    GetOrThrowNotFoundService GetOrThrowNotFoundService;

    public List<JobResponseDTO> getJobs(Integer establishmentId) throws EstablishmentNotFoundException {
        Establishment establishment = GetOrThrowNotFoundService.getEstablishmentOrThrowEstablishmentNotFound(establishmentId);
        List<JobResponseDTO> jobs = establishment.getJobs().stream().map(JobResponseDTO::new).toList();
        return jobs;
    }

    public JobResponseDTO getJob(Integer id) throws JobNotFoundException {
        JobResponseDTO job = new JobResponseDTO(GetOrThrowNotFoundService.getJobOrThrowJobNotFound(id));
        return job;
    }

    public List<JobResponseDTO> saveJob(JobRequestDTO jobRequestDTO) throws EstablishmentNotFoundException {
        Establishment establishment = GetOrThrowNotFoundService.getEstablishmentOrThrowEstablishmentNotFound(jobRequestDTO.establishmentId());
        Job newJob = new Job(jobRequestDTO, establishment);
        jobRepository.save(newJob);
        List<JobResponseDTO> updatedJobs = establishment.getJobs().stream().map(JobResponseDTO::new).toList();
        return updatedJobs;
    }

    public JobResponseDTO updateJob(Integer id, JobRequestDTO jobRequestDTO) {
        Job job = GetOrThrowNotFoundService.getJobOrThrowJobNotFound(id);
        BeanUtils.copyProperties(jobRequestDTO, job, "establishmentId");
        jobRepository.save(job);
        JobResponseDTO updatedJob = new JobResponseDTO(job);
        return updatedJob;
    }

    public void deleteJob(Integer id) {
        Job job = GetOrThrowNotFoundService.getJobOrThrowJobNotFound(id);
        jobRepository.delete(job);
    }
}
