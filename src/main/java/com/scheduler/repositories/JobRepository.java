package com.scheduler.repositories;

import com.scheduler.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
}
