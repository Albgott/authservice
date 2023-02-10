package com.albgott.authservice.account.domain;

import com.albgott.authservice.business.domain.Business;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    void save(Account account);
    Optional<Account> findById(UUID id);
    boolean existsByEmailAndBusinessAndRole(EmailAddress email, Business business, AccountRole role);
}
