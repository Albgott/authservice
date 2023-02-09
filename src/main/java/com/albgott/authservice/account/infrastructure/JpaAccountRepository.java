package com.albgott.authservice.account.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountDTO, UUID> {
}
