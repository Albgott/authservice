package com.albgott.authservice.token.domain.model;

import com.albgott.authservice.token.domain.exception.WrongTokenIDFormat;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class TokenId implements Serializable {
    private UUID value;

    protected TokenId() {
    }

    public TokenId(String id) {
        try {
            this.value = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new WrongTokenIDFormat(id);
        }
    }

    public UUID value() {
        return value;
    }

    @Override
    public String toString() {
        return value().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenId that = (TokenId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}