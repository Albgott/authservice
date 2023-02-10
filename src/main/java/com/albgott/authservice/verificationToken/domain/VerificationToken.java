package com.albgott.authservice.verificationToken.domain;


import com.albgott.authservice.account.domain.Account;
import com.albgott.authservice.commons.domain.AggregateRoot;
import com.albgott.authservice.commons.domain.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class VerificationToken extends AggregateRoot {
    private UUID id;
    private Account account;
    private Timestamp expiresAt;

    public VerificationToken(@NonNull UUID id, @NonNull Account account) {
        this.id = id;
        this.account = account;
        this.expiresAt = Utils.nowPlus(24);
        record(new VerificationTokenCreatedDomainEvent(account.getId().toString()));
    }
}
