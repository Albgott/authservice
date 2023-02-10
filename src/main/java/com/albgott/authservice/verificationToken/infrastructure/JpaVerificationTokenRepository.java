package com.albgott.authservice.verificationToken.infrastructure;

import com.albgott.authservice.account.infrastructure.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaVerificationTokenRepository extends JpaRepository<VerificationTokenDTO, UUID> {
    Optional<VerificationTokenDTO> findByAccount(AccountDTO account);
}
