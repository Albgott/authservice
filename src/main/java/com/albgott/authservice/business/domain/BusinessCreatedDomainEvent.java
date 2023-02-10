package com.albgott.authservice.business.domain;

import com.albgott.authservice.commons.domain.bus.event.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

@Getter
@NoArgsConstructor
public class BusinessCreatedDomainEvent extends DomainEvent {
    private String name;
    private Timestamp createdAt;

    public BusinessCreatedDomainEvent(String aggregateId, String name, Timestamp createdAt) {
        super(aggregateId);
        this.name = name;
        this.createdAt = createdAt;
    }

    public BusinessCreatedDomainEvent(String aggregateId, String eventId, String occurredOn, String name,
                                      Timestamp createdAt) {
        super(aggregateId,eventId,occurredOn);
        this.name = name;
        this.createdAt = createdAt;
    }

    @Override
    public String eventName() {
        return "business.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("name",name);
            put("created_at",createdAt);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId,
                                      HashMap<String, Serializable> body,
                                      String eventId,
                                      String occurredOn) {
        return new BusinessCreatedDomainEvent(
                aggregateId,
                eventId,
                occurredOn,
                (String) body.get("name"),
                (Timestamp) body.get("created_at")
        );
    }

}
