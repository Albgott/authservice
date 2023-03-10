package com.albgott.authservice.business.infrastructure;

import com.albgott.authservice.business.domain.model.Business;
import com.albgott.authservice.business.domain.model.BusinessId;
import com.albgott.authservice.business.domain.model.BusinessName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaBusinessRepository extends JpaRepository<Business, BusinessId> {
    Optional<Business> findByName(BusinessName name);
    boolean existsByName(BusinessName name);
}

