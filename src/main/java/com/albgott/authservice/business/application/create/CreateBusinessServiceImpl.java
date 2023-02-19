package com.albgott.authservice.business.application.create;

import com.albgott.authservice.account.domain.model.*;
import com.albgott.authservice.account.domain.repository.AccountRepository;
import com.albgott.authservice.business.domain.event.BusinessCreatedEvent;
import com.albgott.authservice.business.domain.exception.BusinessNameAlreadyOnUse;
import com.albgott.authservice.business.domain.model.Business;
import com.albgott.authservice.business.domain.model.BusinessId;
import com.albgott.authservice.business.domain.model.BusinessName;
import com.albgott.authservice.business.domain.repository.BusinessRepository;
import com.albgott.authservice.shared.domain.event.EventBus;
import com.albgott.authservice.token.domain.model.Token;
import com.albgott.authservice.token.domain.repository.TokenRepository;
import com.albgott.authservice.token.domain.service.TokenFactory;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CreateBusinessServiceImpl implements CreateBusinessService {
    private final BusinessRepository businessRepository;
    private final AccountRepository accountRepository;
    private final EventBus eventBus;
    private final PasswordEncoder encoder;
    private final TokenRepository tokenRepository;

    public CreateBusinessServiceImpl(
            BusinessRepository businessRepository,
            AccountRepository accountRepository,
            EventBus eventBus, PasswordEncoder encoder, TokenRepository tokenRepository) {
        this.businessRepository = businessRepository;
        this.accountRepository = accountRepository;
        this.eventBus = eventBus;
        this.encoder = encoder;
        this.tokenRepository = tokenRepository;
    }

    public void exec(CreateBusinessCommand command){
        Business business = getBusiness(command);
        Account account = getAccount(command, business);
        Token token = getVerificationToken(account);


        businessRepository.save(business);
        accountRepository.save(account);
        tokenRepository.save(token);

        BusinessCreatedEvent businessCreatedEvent = new BusinessCreatedEvent(business, account, token);
        eventBus.publish(List.of(businessCreatedEvent));
    }



    private Business getBusiness(CreateBusinessCommand command) {
        BusinessId businessId = new BusinessId(command.businessId());
        BusinessName businessName = new BusinessName(command.businessName());
        ensureBusinessNameIsNotTaken(businessName);
        return new Business(businessId,businessName);
    }

    private void ensureBusinessNameIsNotTaken(BusinessName businessName) {
        if(businessRepository.existsByName(businessName))
            throw new BusinessNameAlreadyOnUse(businessName.value());
    }

    private Account getAccount(CreateBusinessCommand command, Business business) {
        Credentials credentials = new Credentials(
                new AccountName(command.accountName()),
                new Email(command.email()),
                new EncryptedPassword(command.password(), encoder)
        );

        return new Account(
                new AccountID(command.accountId()),
                credentials,
                business,
                AccountRole.BUSINESS_OWNER
        );
    }
    private Token getVerificationToken(Account account) {
        TokenFactory tokenGenerator = new TokenFactory();
        return tokenGenerator.generateVerificationToken(account);
    }
}
