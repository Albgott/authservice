package com.albgott.authservice.shared.infrastructure.exception;

import com.albgott.authservice.shared.domain.exception.DomainException;

public class ErrorMessage {

    private final String exception;
    private final String code;
    private final String message;
    private final String path;

    public ErrorMessage(DomainException exception, String path) {
        this.exception = exception.getClass().getSimpleName();
        this.code = exception.code();
        this.message = exception.message();
        this.path = path;
    }

    @Override
    public String toString() {
        return "{" +
                "exception='" + exception + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
