package com.albgott.authservice.account.infrastructure;

import com.albgott.authservice.account.domain.model.Account;
import com.albgott.authservice.account.domain.model.AccountID;
import com.albgott.authservice.account.domain.model.AccountRole;
import com.albgott.authservice.business.domain.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAccountRepository extends JpaRepository<Account, AccountID> {
    @Query("SELECT a FROM accounts a WHERE a.credentials.email.address = :email AND a.role = :role" )
    Optional<Account> findByCredentialsEmailAndRole(@Param("email") String email,@Param("role") AccountRole role);

    @Query("SELECT a FROM accounts a WHERE a.credentials.email.address = :email AND a.role = :role AND a.business = " +
            ":business")
    Optional<Account> findByCredentialsEmailAndBusinessAndRole(
            @Param("email") String email,
            @Param("business") Business business,
            @Param("role") AccountRole role
    );
}
