package com.albgott.authservice.business.application.create;

import com.albgott.authservice.account.application.create.CreateBusinessOwnerAccountRequest;
import com.albgott.authservice.account.application.create.CreateBusinessOwnerAccountUseCase;
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
    private final CreateBusinessOwnerAccountUseCase createBusinessOwnerAccountUseCase;

    public CreateBusinessUseCase(EventBus eventBus, BusinessRepository businessRepository,
                                 CreateBusinessOwnerAccountUseCase createBusinessOwnerAccountUseCase) {
        this.eventBus = eventBus;
        this.businessRepository = businessRepository;
        this.createBusinessOwnerAccountUseCase = createBusinessOwnerAccountUseCase;
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
        createBusinessOwnerAccountUseCase.execute(new CreateBusinessOwnerAccountRequest(
                request.getId(),
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getBusiness().id()
        ));
    }
}
