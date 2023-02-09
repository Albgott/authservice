package com.albgott.authservice.commons.domain;

import com.albgott.authservice.commons.domain.bus.event.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {
    private List<DomainEvent> domainEvents = new ArrayList<>();

    final public List<DomainEvent> pullDomainEvents(){
        var events = domainEvents;
        domainEvents = new ArrayList<>();
        return events;
    }

    final protected void record(DomainEvent event){
        domainEvents.add(event);
    }
}
