package com.albgott.authservice.account.domain.exception;

import com.albgott.authservice.shared.domain.exception.WrongFormatException;

public final class WrongEmailFormat extends WrongFormatException {
    public WrongEmailFormat(String mail) {
        super("WRONG_EMAIL_FORMAT", "The value \"" + mail + "\" has not a valid email format.");
    }
}
