package com.albgott.authservice.business.application;

import com.albgott.authservice.business.domain.Business;
import com.albgott.authservice.business.domain.BusinessName;
import com.albgott.authservice.commons.application.Request;
import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public final class CreateBusinessRequest implements Request {
    UUID id;
    String name;
    String password;
    String email;
    Business business;

    public CreateBusinessRequest(
            @NonNull UUID id,
            @NonNull String name,
            @NonNull String password,
            @NonNull String email,
            @NonNull UUID businessId,
            @NonNull String businessName
    ) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.business = new Business(
                businessId,
                new BusinessName(businessName)
        );
    }
}
