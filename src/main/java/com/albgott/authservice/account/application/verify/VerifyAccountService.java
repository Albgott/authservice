package com.albgott.authservice.account.application.verify;

import com.albgott.authservice.account.domain.model.Account;
import com.albgott.authservice.account.domain.repository.AccountRepository;
import com.albgott.authservice.shared.application.CommandUseCase;
import com.albgott.authservice.shared.domain.event.EventBus;
import com.albgott.authservice.token.domain.exception.TokenHasExpiredException;
import com.albgott.authservice.token.domain.exception.TokenNotFound;
import com.albgott.authservice.token.domain.model.Token;
import com.albgott.authservice.token.domain.model.TokenId;
import com.albgott.authservice.token.domain.repository.TokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VerifyAccountService implements CommandUseCase<VerifyAccountCommand> {

    private final TokenRepository tokenRepository;
    private final AccountRepository accountRepository;
    private final EventBus eventBus;

    public VerifyAccountService(TokenRepository tokenRepository, AccountRepository accountRepository, EventBus eventBus) {
        this.tokenRepository = tokenRepository;
        this.accountRepository = accountRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void exec(VerifyAccountCommand command) {
        Token token = getToken(command);
        Account account = token.account();

        if(token.hasExpired() || !token.isVerificationToken())
            throw new TokenHasExpiredException(token.id());

        account.verifyEmail();

        accountRepository.save(account);
        tokenRepository.delete(token);
        eventBus.publish(account.pullDomainEvents());
    }

    private Token getToken(VerifyAccountCommand command) {
        return tokenRepository.findById(
                new TokenId(command.tokenId())
        ).orElseThrow(() -> new TokenNotFound(command.tokenId()));
    }
}
