package com.albgott.authservice.account.application.create;

import com.albgott.authservice.account.domain.Account;
import com.albgott.authservice.account.domain.AccountRepository;
import com.albgott.authservice.account.domain.AccountRole;
import com.albgott.authservice.account.domain.BusinessAccountCreatedDomainEvent;
import com.albgott.authservice.business.domain.Business;
import com.albgott.authservice.business.domain.BusinessRepository;
import com.albgott.authservice.commons.application.CommandUseCase;
import com.albgott.authservice.commons.domain.bus.event.EventBus;
import com.albgott.authservice.verificationToken.application.generate.GenerateVerificationTokenRequest;
import com.albgott.authservice.verificationToken.application.generate.GenerateVerificationTokenUseCase;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CreateBusinessOwnerAccountUseCase implements CommandUseCase<CreateBusinessOwnerAccountRequest> {

    @Autowired
    private EventBus eventBus;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private GenerateVerificationTokenUseCase generateVerificationTokenUseCase;

    @Transactional
    @Override
    public void execute(CreateBusinessOwnerAccountRequest request) {
        Business business = getBusinessIfExist(request.getBusinessId());
        Account account = new Account(
                request.getId(),
                request.getAccountName(),
                AccountRole.BUSINESS,
                request.getPassword().encrypt(encoder),
                business,
                request.getEmailAddress()
        );
        ensureCanCreateAccount(account);
        accountRepository.save(account);
        eventBus.publish(List.of(new BusinessAccountCreatedDomainEvent(
                account.getId().toString(),
                account.getName().get(),
                account.getEmail().getAddress(),
                account.getBusiness().id()
        )));
        generateVerificationTokenUseCase.execute(new GenerateVerificationTokenRequest(account.getId()));

    }

    private void ensureCanCreateAccount(Account account) {
        if(accountRepository.existsByEmailAndBusinessAndRole(account.getEmail(), account.getBusiness(),
                account.getRole()))
            throw new RuntimeException("Unable to create account");
    }

    private Business getBusinessIfExist(UUID businessId) {
        return businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("business does not exist"));
    }
}
