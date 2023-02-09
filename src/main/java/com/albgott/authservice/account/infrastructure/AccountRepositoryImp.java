package com.albgott.authservice.account.infrastructure;

import com.albgott.authservice.account.application.AccountRepository;

public class AccountRepositoryImp implements AccountRepository {

    private final JpaAccountRepository jpaAccountRepository;

    public AccountRepositoryImp(JpaAccountRepository jpaAccountRepository) {
        this.jpaAccountRepository = jpaAccountRepository;
    }
}
