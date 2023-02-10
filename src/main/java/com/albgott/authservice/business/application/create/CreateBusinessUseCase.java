package com.albgott.authservice.business.application;

import com.albgott.authservice.business.domain.Business;
import com.albgott.authservice.business.domain.BusinessRepository;
import com.albgott.authservice.commons.application.CommandUseCase;
import com.albgott.authservice.commons.domain.bus.event.EventBus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateBusinessUseCase implements CommandUseCase<CreateBusinessRequest> {

    private final EventBus eventBus;
    private final BusinessRepository businessRepository;

    public CreateBusinessUseCase(EventBus eventBus, BusinessRepository businessRepository) {
        this.eventBus = eventBus;
        this.businessRepository = businessRepository;
    }

    @Transactional
    public void execute(CreateBusinessRequest request) {
        createBusiness(request.getBusiness());
        createOwnerAccount(request);
    }

    private void createBusiness(Business business) {
        checkBusinessCanBeCreated(business);
        businessRepository.save(business);
        eventBus.publish(business.pullDomainEvents());
    }

    private void checkBusinessCanBeCreated(Business business) {
        if(businessRepository.exists(business.getId()))
            throw new RuntimeException("businessId on use");
        if(businessRepository.exists(business.getName()))
            throw new RuntimeException("businessName on use");
    }

    private void createOwnerAccount(CreateBusinessRequest request) {
    }
}
