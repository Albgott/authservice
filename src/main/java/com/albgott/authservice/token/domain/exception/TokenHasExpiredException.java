package com.albgott.authservice.token.domain.exception;

import com.albgott.authservice.shared.domain.exception.WrongFormatException;

public final class TokenHasExpiredException extends WrongFormatException {
    public TokenHasExpiredException(String id) {
        super("TOKEN_HAS_EXPIRED", String.format("Token with id '%s' has expired.",id));
    }
}
