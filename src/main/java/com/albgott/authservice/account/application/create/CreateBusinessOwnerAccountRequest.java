package com.albgott.authservice.account.application;

import com.albgott.authservice.account.domain.AccountName;
import com.albgott.authservice.account.domain.EmailAddress;
import com.albgott.authservice.account.domain.Password;
import com.albgott.authservice.commons.application.Request;
import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class CreateBusinessOwnerAccountRequest implements Request {
    private UUID id;
    private AccountName accountName;
    private EmailAddress emailAddress;
    private Password password;
    private UUID businessId;

    public CreateBusinessOwnerAccountRequest(
            @NonNull UUID id,
            @NonNull String name,
            @NonNull String email,
            @NonNull String password,
            @NonNull UUID businessId
    ){
        this.id = id;
        this.accountName = new AccountName(name);
        this.emailAddress = new EmailAddress(email);
        this.password = new Password(password);
        this.businessId = businessId;
    }
}
