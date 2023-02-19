package com.albgott.authservice.account.domain.repository;

import com.albgott.authservice.account.domain.model.Account;
import com.albgott.authservice.business.domain.model.Business;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findSystemAccount(String email);
    Optional<Account> findBusinessWorkerAccount(String email, Business business);
    Optional<Account> findBusinessClientAccount(String email, Business business);
    void save(Account account);
}
