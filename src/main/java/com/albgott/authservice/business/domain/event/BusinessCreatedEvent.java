package com.albgott.authservice.business.domain.event;

import com.albgott.authservice.account.domain.model.Account;
import com.albgott.authservice.business.domain.model.Business;
import com.albgott.authservice.shared.domain.event.DomainEvent;
import com.albgott.authservice.token.domain.model.Token;

import java.io.Serializable;
import java.util.HashMap;

public class BusinessCreatedEvent extends DomainEvent {
    private String businessName;
    private String accountId;
    private String accountName;
    private String email;
    private String createdAt;
    private String verificationToken;

    public BusinessCreatedEvent() {
    }

    public BusinessCreatedEvent(Business business, Account account, Token token) {
        super(business.id().toString());
        this.businessName = business.getName().value();
        this.accountId = account.id().toString();
        this.accountName = account.accountName();
        this.email = account.email();
        this.createdAt = business.createdAt().toString();
        this.verificationToken = token.id();
    }

    public BusinessCreatedEvent(
            String aggregateId,
            String eventId,
            String occurredOn,
            String businessName,
            String accountId,
            String accountName,
            String email,
            String createdAt,
            String verificationToken
    ) {
        super(aggregateId, eventId, occurredOn);
        this.businessName = businessName;
        this.accountId = accountId;
        this.accountName = accountName;
        this.email = email;
        this.createdAt = createdAt;
        this.verificationToken = verificationToken;
    }

    @Override
    public String eventName() {
        return "business.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("business_name",businessName);
            put("account_id",accountId);
            put("account_name",accountName);
            put("email",email);
            put("created_at",createdAt);
            put("verification_token",verificationToken);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId,
                                      HashMap<String, Serializable> body,
                                      String eventId,
                                      String occurredOn) {
        return new BusinessCreatedEvent(
                aggregateId,eventId,occurredOn,
                (String) body.get("business_name"),
                (String) body.get("account_id"),
                (String) body.get("account_name"),
                (String) body.get("email"),
                (String) body.get("created_at"),
                (String) body.get("verification_token")
        );
    }

    public String businessName() {
        return businessName;
    }

    public String accountId() {
        return accountId;
    }

    public String accountName() {
        return accountName;
    }

    public String email() {
        return email;
    }

    public String createdAt() {
        return createdAt;
    }

    public String verificationToken() {
        return verificationToken;
    }
}
