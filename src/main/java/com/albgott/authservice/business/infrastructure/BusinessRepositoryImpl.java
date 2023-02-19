package com.albgott.authservice.business.infrastructure;

import com.albgott.authservice.business.domain.model.Business;
import com.albgott.authservice.business.domain.model.BusinessId;
import com.albgott.authservice.business.domain.model.BusinessName;
import com.albgott.authservice.business.domain.repository.BusinessRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BusinessRepositoryImpl implements BusinessRepository {
    private final JpaBusinessRepository repository;

    public BusinessRepositoryImpl(JpaBusinessRepository repository) {
        this.repository = repository;
    }


    @Override
    public void save(Business business) {
        repository.save(business);
    }

    @Override
    public void delete(Business business) {
        repository.delete(business);
    }

    @Override
    public void delete(BusinessId businessId) {
        repository.deleteById(businessId);
    }

    @Override
    public Optional<Business> findById(BusinessId id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Business> findByName(BusinessName name) {
        return repository.findByName(name);
    }

    @Override
    public boolean existsByName(BusinessName name) {
        return repository.existsByName(name);
    }
}
