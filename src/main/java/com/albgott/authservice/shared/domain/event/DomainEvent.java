package com.albgott.authservice.shared.domain.event;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public abstract class DomainEvent {
    private String aggregateId;
    private String eventId;
    private String occurredOn;

    public DomainEvent() {
    }

    public DomainEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        this.eventId = UUID.randomUUID().toString();
        var dateTime = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.occurredOn = formatter.format(dateTime);
        validate();
    }

    public DomainEvent(String aggregateId, String eventId, String occurredOn) {
        this.aggregateId = aggregateId;
        this.eventId = eventId;
        this.occurredOn = occurredOn;
        validate();
    }

    public String aggregateId() {
        return aggregateId;
    }

    public String eventId() {
        return eventId;
    }

    public String occurredOn() {
        return occurredOn;
    }

    public abstract String eventName();

    public abstract HashMap<String, Serializable> toPrimitives();

    public abstract DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId
            , String occurredOn);

    public void validate() {
        if (aggregateId == null || aggregateId.isEmpty()) {
            throw new IllegalArgumentException("Aggregate ID must not be null or empty.");
        }
        if (eventId == null || eventId.isEmpty()) {
            throw new IllegalArgumentException("Event ID must not be null or empty.");
        }
        if (occurredOn == null) {
            throw new IllegalArgumentException("Occurred on must not be null.");
        }
    }
}