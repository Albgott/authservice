package com.albgott.authservice.account.infrastructure;

import com.albgott.authservice.account.domain.model.Account;
import com.albgott.authservice.account.domain.model.AccountRole;
import com.albgott.authservice.account.domain.repository.AccountRepository;
import com.albgott.authservice.business.domain.model.Business;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final JpaAccountRepository repository;

    public AccountRepositoryImpl(JpaAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Account> findSystemAccount(String email) {
        return repository.findByCredentialsEmailAndRole(email, AccountRole.ADMIN);
    }

    @Override
    public Optional<Account> findBusinessWorkerAccount(String email, Business business) {
        Optional<Account> account = repository.findByCredentialsEmailAndBusinessAndRole(email,business,AccountRole.BUSINESS_OWNER);

        if(account.isPresent()) return account;
        return repository.findByCredentialsEmailAndBusinessAndRole(email,business,AccountRole.BUSINESS_EMPLOYEE);
    }

    @Override
    public Optional<Account> findBusinessClientAccount(String email, Business business) {
        return repository.findByCredentialsEmailAndBusinessAndRole(email,business,AccountRole.BUSINESS_CLIENT);
    }

    @Override
    public void save(Account account) {
        repository.save(account);
    }
}
