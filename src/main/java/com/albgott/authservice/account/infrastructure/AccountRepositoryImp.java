package com.albgott.authservice.account.infrastructure;

import com.albgott.authservice.account.domain.Account;
import com.albgott.authservice.account.domain.AccountRepository;
import com.albgott.authservice.account.domain.AccountRole;
import com.albgott.authservice.account.domain.EmailAddress;
import com.albgott.authservice.business.domain.Business;
import com.albgott.authservice.business.infrastructure.BusinessDTO;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountRepositoryImp implements AccountRepository {

    private final JpaAccountRepository repository;

    public AccountRepositoryImp(JpaAccountRepository jpaAccountRepository) {
        this.repository = jpaAccountRepository;
    }

    @Override
    public void save(Account account) {
        repository.save(AccountDTO.from(account));
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return repository.findById(id)
                .map(AccountDTO::fromThis);
    }

    @Override
    public boolean existsByEmailAndBusinessAndRole(EmailAddress emailAddress, Business business, AccountRole role) {
        return repository.existsByEmailAndBusinessAndRole(emailAddress.getAddress(),BusinessDTO.from(business),role);
    }
}
