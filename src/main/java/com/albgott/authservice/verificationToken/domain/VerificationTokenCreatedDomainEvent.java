package com.albgott.authservice.verificationToken.domain;

import com.albgott.authservice.commons.domain.bus.event.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

@Getter
@NoArgsConstructor
public class VerificationTokenCreatedDomainEvent extends DomainEvent {
    public VerificationTokenCreatedDomainEvent(String aggregateId) {
        super(aggregateId);
    }

    public VerificationTokenCreatedDomainEvent(String aggregateId, String eventId, String occurredOn) {
        super(aggregateId, eventId, occurredOn);
    }

    @Override
    public String eventName() {
        return "email-verification-token.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>();
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId,
                                      HashMap<String, Serializable> body,
                                      String eventId,
                                      String occurredOn) {
        return new VerificationTokenCreatedDomainEvent(aggregateId,eventId,occurredOn);
    }
}
