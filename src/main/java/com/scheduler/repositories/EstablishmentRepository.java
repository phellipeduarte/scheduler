package com.scheduler.repositories;

import com.scheduler.models.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {
}
