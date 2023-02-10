package com.albgott.authservice.account.domain;

import com.albgott.authservice.commons.domain.bus.event.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class BusinessAccountCreatedDomainEvent extends DomainEvent {
    private String name;
    private String email;
    private UUID businessId;

    public BusinessAccountCreatedDomainEvent(String aggregateId, String name, String email, UUID businessId) {
        super(aggregateId);
        this.name = name;
        this.email = email;
        this.businessId = businessId;
    }

    public BusinessAccountCreatedDomainEvent(String aggregateId,
                                             String eventId,
                                             String occurredOn,
                                             String name,
                                             String email,
                                             UUID businessId) {
        super(aggregateId, eventId, occurredOn);
        this.name = name;
        this.email = email;
        this.businessId = businessId;
    }

    @Override
    public String eventName() {
        return "business.account.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("name",name);
            put("email",email);
            put("business_id",businessId);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId,
                                      HashMap<String, Serializable> body,
                                      String eventId,
                                      String occurredOn) {
        return new BusinessAccountCreatedDomainEvent(
                aggregateId,
                eventId,
                occurredOn,
                (String) body.get("name"),
                (String) body.get("email"),
                (UUID) body.get("business_id")
        );
    }
}
