package com.albgott.authservice.account.domain.exception;

import com.albgott.authservice.shared.domain.exception.WrongFormatException;

public final class WrongPasswordFormat extends WrongFormatException {
    public WrongPasswordFormat() {
        super("WRONG_PASSWORD_FORMAT", "Password has not a valid format.");
    }
}
