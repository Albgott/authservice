package com.albgott.authservice.shared.domain.event;

import java.util.List;

public interface EventBus {
    void publish(final List<DomainEvent> events);
}
