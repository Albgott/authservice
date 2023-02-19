package com.albgott.authservice.token.infrastructure;

import com.albgott.authservice.token.domain.model.Token;
import com.albgott.authservice.token.domain.model.TokenId;
import com.albgott.authservice.token.domain.repository.TokenRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TokenRepositoryImpl implements TokenRepository {
    private final JpaTokenRepository repository;

    public TokenRepositoryImpl(JpaTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Token token) {
        repository.save(token);
    }

    @Override
    public Optional<Token> findById(TokenId id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(TokenId id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Token token) {
        repository.delete(token);
    }
}
