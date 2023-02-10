package com.albgott.authservice.account.application;

import com.albgott.authservice.account.domain.Account;
import com.albgott.authservice.business.domain.Business;
import com.albgott.authservice.business.domain.BusinessRepository;
import com.albgott.authservice.commons.application.CommandUseCase;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateBusinessOwnerAccountUseCase implements CommandUseCase<CreateBusinessOwnerAccountRequest> {

    @Autowired
    private BusinessRepository businessRepository;

    @Transactional
    @Override
    public void execute(CreateBusinessOwnerAccountRequest request) {
        Business business = getBusinessIfExist(request.getBusinessId());
        Account account
    }

    private Business getBusinessIfExist(UUID businessId) {
        businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("business does not exist"));
    }
}
