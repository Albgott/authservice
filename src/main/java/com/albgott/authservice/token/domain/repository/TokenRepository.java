package com.albgott.authservice.token.domain.repository;

import com.albgott.authservice.token.domain.model.Token;
import com.albgott.authservice.token.domain.model.TokenId;

import java.util.Optional;

public interface TokenRepository {
    void save(Token token);
    Optional<Token> findById(TokenId id);
    void deleteById(TokenId id);
    void delete(Token token);
}
