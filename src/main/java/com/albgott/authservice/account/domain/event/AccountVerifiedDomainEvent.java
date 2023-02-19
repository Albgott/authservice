package com.albgott.authservice.account.domain.event;

import com.albgott.authservice.shared.domain.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class AccountVerifiedDomainEvent extends DomainEvent {

    public AccountVerifiedDomainEvent() {
    }

    public AccountVerifiedDomainEvent(String aggregateId) {
        super(aggregateId);
    }

    public AccountVerifiedDomainEvent(String aggregateId, String eventId, String occurredOn) {
        super(aggregateId, eventId, occurredOn);
    }

    @Override
    public String eventName() {
        return "account.verified";
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
        return new AccountVerifiedDomainEvent(aggregateId,eventId,occurredOn);
    }
}
