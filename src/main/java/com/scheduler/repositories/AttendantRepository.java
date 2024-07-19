package com.scheduler.repositories;

import com.scheduler.models.Attendant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AttendantRepository extends JpaRepository<Attendant, UUID> {
    Optional<Attendant> findByUuid(UUID uuid);
}
