package com.albgott.authservice.account.domain.model;

import com.albgott.authservice.account.domain.exception.WrongAccountIdFormat;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class AccountID implements Serializable {
    private UUID value;

    protected AccountID(){}

    public AccountID(String id) {
        try {
            this.value = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new WrongAccountIdFormat(id);
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
        AccountID that = (AccountID) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
