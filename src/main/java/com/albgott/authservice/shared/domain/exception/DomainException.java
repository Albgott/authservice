package com.albgott.authservice.shared.domain.exception;

public abstract class DomainException extends RuntimeException{
    String code;
    String message;
    public DomainException(String code,String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}
