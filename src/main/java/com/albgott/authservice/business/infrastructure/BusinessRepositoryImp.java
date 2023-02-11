package com.albgott.authservice.business.infrastructure;

import com.albgott.authservice.business.application.BusinessDTO;
import com.albgott.authservice.business.domain.Business;
import com.albgott.authservice.business.domain.BusinessName;
import com.albgott.authservice.business.domain.BusinessRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class BusinessRepositoryImp implements BusinessRepository {
    private final JpaBusinessRepository repository;

    public BusinessRepositoryImp(JpaBusinessRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Business business) {
        repository.save(BusinessDTO.from(business));
    }

    @Override
    public boolean exists(BusinessName businessName) {
        return repository.existsByName(businessName.getValue());
    }

    @Override
    public boolean exists(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Business> findByName(BusinessName name) {
        return repository.findByName(name.getValue())
                .map(BusinessDTO::fromThis);
    }

    @Override
    public Optional<Business> findById(UUID id) {
        return repository.findById(id)
                .map(BusinessDTO::fromThis);
    }
}
