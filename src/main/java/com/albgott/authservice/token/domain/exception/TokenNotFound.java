package com.albgott.authservice.token.domain.exception;

import com.albgott.authservice.shared.domain.exception.NotFoundExeption;

public final class TokenNotFound extends NotFoundExeption {
    public TokenNotFound(String id) {
        super("TOKEN_NOT_FOUND", String.format("Token with id '%s' not found.",id));
    }
}
