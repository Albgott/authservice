package com.albgott.authservice.token.infrastructure;

import com.albgott.authservice.token.domain.model.Token;
import com.albgott.authservice.token.domain.model.TokenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTokenRepository extends JpaRepository<Token, TokenId> {
}
