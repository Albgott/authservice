package com.albgott.authservice.account.infrastructure;

import com.albgott.authservice.account.domain.AccountRole;
import com.albgott.authservice.business.application.BusinessDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountDTO, UUID> {
    boolean existsByEmailAndBusinessAndRole(String email, BusinessDTO business, AccountRole role);
}
