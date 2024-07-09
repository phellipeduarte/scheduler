package com.scheduler.controllers;

import com.scheduler.dtos.JobRequestDTO;
import com.scheduler.dtos.JobResponseDTO;
import com.scheduler.exceptions.EstablishmentNotFoundException;
import com.scheduler.exceptions.JobNotFoundException;
import com.scheduler.services.JobService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;


    @GetMapping("/establishment/{establishment_id}")
    public ResponseEntity getJobs(@PathVariable(value = "establishment_id") Integer establishmentId){
        try {
            List<JobResponseDTO> jobs = jobService.getJobs(establishmentId);
            return ResponseEntity.status(HttpStatus.OK).body(jobs);
        } catch (EstablishmentNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getJob(@PathVariable(value = "id") Integer id){
        try {
            JobResponseDTO job = jobService.getJob(id);
            return ResponseEntity.status(HttpStatus.OK).body(job);
        } catch (JobNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity saveJob(@RequestBody JobRequestDTO jobRequestDTO){
        List<JobResponseDTO> jobs = jobService.saveJob(jobRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobs);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateJob(@PathVariable(value = "id") Integer id, @RequestBody JobRequestDTO jobRequestDTO){
        try {
            JobResponseDTO updatedJob = jobService.updateJob(id, jobRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updatedJob);
        } catch (JobNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteJob(@PathVariable(value = "id") Integer id){
        try {
            jobService.deleteJob(id);
            return ResponseEntity.status(HttpStatus.OK).body("Serviço removido permanentemente.");
        } catch (JobNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
