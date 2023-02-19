package com.albgott.authservice.shared.domain.model;

import com.albgott.authservice.shared.domain.event.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    final public List<DomainEvent> pullDomainEvents(){
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return events;
    }

    final protected void record(DomainEvent event){
        domainEvents.add(event);
    }
}
