package com.albgott.authservice.shared.domain.exception;

public class BadParamException extends DomainException {
    public BadParamException(String code, String message) {
        super(code, message);
    }
}
