package com.albgott.authservice.business.domain;

import com.albgott.authservice.commons.domain.AggregateRoot;
import com.albgott.authservice.commons.domain.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;


@Getter
@AllArgsConstructor
public class Business extends AggregateRoot {
    private UUID id;
    private BusinessName name;
    private Timestamp createdAt = Utils.now();

    public Business(UUID id, BusinessName name) {
        assert id != null;
        assert name != null;
        this.id = id;
        this.name = name;
        record(new BusinessCreatedDomainEvent(id.toString(),name(), createdAt));
    }

    public String name(){
        return name.getValue();
    }

    public UUID id(){
        return id;
    }

}
