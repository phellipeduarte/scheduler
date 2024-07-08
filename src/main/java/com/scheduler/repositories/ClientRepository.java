package com.scheduler.repositories;

import com.scheduler.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> findByPhone(String phone);
    Optional<Client> findByUuid(UUID uuid);
}
