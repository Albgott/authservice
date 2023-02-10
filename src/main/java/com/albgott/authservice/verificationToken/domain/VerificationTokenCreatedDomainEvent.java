package com.albgott.authservice.verificationToken.domain;

import com.albgott.authservice.commons.domain.bus.event.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class VerificationTokenCreatedDomainEvent extends DomainEvent {
    private UUID accountId;
    public VerificationTokenCreatedDomainEvent(String aggregateId, UUID accountId) {
        super(aggregateId);
        this.accountId = accountId;
    }

    public VerificationTokenCreatedDomainEvent(String aggregateId, String eventId, String occurredOn, UUID accountId) {
        super(aggregateId, eventId, occurredOn);
        this.accountId = accountId;
    }

    @Override
    public String eventName() {
        return "email-verification-token.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("account_id", accountId);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId,
                                      HashMap<String, Serializable> body,
                                      String eventId,
                                      String occurredOn) {
        return new VerificationTokenCreatedDomainEvent(aggregateId,eventId,occurredOn,(UUID) body.get("account_id"));
    }
}
