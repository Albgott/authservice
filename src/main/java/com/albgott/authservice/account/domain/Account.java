package com.albgott.authservice.account.domain;

import com.albgott.authservice.business.domain.Business;
import com.albgott.authservice.commons.domain.AggregateRoot;
import com.albgott.authservice.commons.domain.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Account extends AggregateRoot {
    private UUID id;
    private AccountName name;
    private AccountRole role;
    private EncryptedPassword password;
    private Business business;
    private EmailAddress email;
    private Timestamp createdAt;

    public Account(
            @NonNull UUID id,
            @NonNull AccountName name,
            @NonNull AccountRole role,
            @NonNull EncryptedPassword password,
            @NonNull Business business,
            @NonNull EmailAddress email
    ) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.password = password;
        this.business = business;
        this.email = email;
        this.createdAt = Utils.now();
    }
}
