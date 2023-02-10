package com.albgott.authservice.verificationToken.infrastructure;

import com.albgott.authservice.account.domain.Account;
import com.albgott.authservice.account.infrastructure.AccountDTO;
import com.albgott.authservice.business.infrastructure.BusinessDTO;
import com.albgott.authservice.verificationToken.domain.VerificationToken;
import com.albgott.authservice.verificationToken.domain.VerificationTokenRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class VerificationTokenRepositoryImp implements VerificationTokenRepository {
    private final JpaVerificationTokenRepository repository;

    public VerificationTokenRepositoryImp(JpaVerificationTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<VerificationToken> findById(UUID id) {
        return repository.findById(id)
                .map(VerificationTokenDTO::fromThis);
    }

    @Override
    public void save(VerificationToken verificationToken) {
        repository.save(VerificationTokenDTO.from(verificationToken));
    }

    @Override
    public void delete(VerificationToken verificationToken) {
        repository.delete(VerificationTokenDTO.from(verificationToken));
    }

    @Override
    public Optional<VerificationToken> findByAccount(Account account) {
        return repository.findByAccount(AccountDTO.from(account))
                .map(VerificationTokenDTO::fromThis);
    }
}
