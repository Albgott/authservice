package com.albgott.authservice.business.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaBusinessRepository extends JpaRepository<BusinessDTO, UUID> {
    boolean existsByName(String name);
    Optional<BusinessDTO> findByName(String name);
}
