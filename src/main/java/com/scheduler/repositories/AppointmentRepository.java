package com.scheduler.repositories;

import com.scheduler.models.Appointment;
import com.scheduler.models.Attendant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    Optional<Appointment> findByUuid(UUID uuid);
    List<Appointment> findAllByAttendant(Attendant attendant);
}
