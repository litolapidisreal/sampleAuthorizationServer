package com.sampleAuth.server.repository;

import com.sampleAuth.server.models.CustomClient;
import com.sampleAuth.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<CustomClient, Long> {
    Optional<CustomClient> findByClientId(String email);
}
