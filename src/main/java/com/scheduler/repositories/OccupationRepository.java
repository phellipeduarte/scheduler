package com.scheduler.repositories;

import com.scheduler.models.Attendant;
import com.scheduler.models.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OccupationRepository extends JpaRepository<Occupation, Integer> {
    List<Occupation> findByAttendant(Attendant attendant);
}
