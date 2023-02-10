package com.albgott.authservice.business.domain;

import java.util.Optional;
import java.util.UUID;

public interface BusinessRepository {
    void save(Business business);
    boolean exists(BusinessName businessName);
    boolean exists(UUID id);
    Optional<Business> findByName(BusinessName name);
    Optional<Business> findById(UUID id);
}
