package com.albgott.authservice.business.domain.repository;

import com.albgott.authservice.business.domain.model.Business;
import com.albgott.authservice.business.domain.model.BusinessId;
import com.albgott.authservice.business.domain.model.BusinessName;

import java.util.Optional;

public interface BusinessRepository {
    void save(Business business);
    void delete(Business business);
    void delete(BusinessId businessId);
    Optional<Business> findById(BusinessId id);
    Optional<Business> findByName(BusinessName name);
    boolean existsByName(BusinessName name);
}
