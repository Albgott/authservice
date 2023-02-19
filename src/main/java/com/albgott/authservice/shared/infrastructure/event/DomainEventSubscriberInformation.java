package com.albgott.authservice.shared.infrastructure.event;

import com.albgott.authservice.shared.domain.Utils;
import com.albgott.authservice.shared.domain.event.DomainEvent;

import java.util.List;

public final class DomainEventSubscriberInformation {
    private final Class<?> subscriberClass;
    private final List<Class<? extends DomainEvent>> subscribedEvents;

    public DomainEventSubscriberInformation(
            Class<?> subscriberClass,
            List<Class<? extends DomainEvent>> subscribedToEvents
    ) {
        this.subscriberClass  = subscriberClass;
        this.subscribedEvents = subscribedToEvents;
    }

    public Class<?> subscriberClass() {
        return subscriberClass;
    }

    public String serviceName() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[2];
    }

    public String aggregateName() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[3];
    }

    public String className() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[nameParts.length - 1];
    }

    public List<Class<? extends DomainEvent>> subscribedToEvents() {
        return subscribedEvents;
    }

    public String formatRabbitMqQueueName() {
        return String.format("albgott.%s.%s.%s", serviceName(), aggregateName(), Utils.toSnake(className()));
    }
}