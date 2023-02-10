package com.albgott.authservice.verificationToken.application.generate;

import com.albgott.authservice.account.domain.Account;
import com.albgott.authservice.account.domain.AccountRepository;
import com.albgott.authservice.commons.application.CommandUseCase;
import com.albgott.authservice.commons.domain.bus.event.EventBus;
import com.albgott.authservice.verificationToken.domain.VerificationToken;
import com.albgott.authservice.verificationToken.domain.VerificationTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenerateVerificationTokenUseCase implements CommandUseCase<GenerateVerificationTokenRequest> {
    private final VerificationTokenRepository tokenRepository;
    private final AccountRepository accountRepository;
    private final EventBus eventBus;

    public GenerateVerificationTokenUseCase(VerificationTokenRepository tokenRepository,
                                            AccountRepository accountRepository,
                                            EventBus eventBus) {
        this.tokenRepository = tokenRepository;
        this.accountRepository = accountRepository;
        this.eventBus = eventBus;
    }

    @Transactional
    @Override
    public void execute(GenerateVerificationTokenRequest request) {
        Account account = getAccountIfExist(request.accountId());
        deleteTokenIfExistForAccount(account);
        VerificationToken token = new VerificationToken(UUID.randomUUID(),account);
        tokenRepository.save(token);
        eventBus.publish(token.pullDomainEvents());
    }

    private void deleteTokenIfExistForAccount(Account account) {
        tokenRepository.findByAccount(account).ifPresent(tokenRepository::delete);
    }

    private Account getAccountIfExist(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("account does not exist"));
    }
}
