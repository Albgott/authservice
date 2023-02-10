package com.albgott.authservice.verificationToken.domain;

import com.albgott.authservice.account.domain.Account;

import java.util.Optional;
import java.util.UUID;

public interface VerificationTokenRepository {
    Optional<VerificationToken> findById(UUID id);
    void save(VerificationToken verificationToken);
    void delete(VerificationToken verificationToken);
    Optional<VerificationToken> findByAccount(Account account);
}
