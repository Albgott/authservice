package com.albgott.authservice.commons.domain;

public abstract class DomainException extends RuntimeException {
    public DomainException(String message){
        super(message);
    }
}
