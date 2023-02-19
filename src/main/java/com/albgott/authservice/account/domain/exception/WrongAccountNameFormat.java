package com.albgott.authservice.account.domain.exception;

import com.albgott.authservice.shared.domain.exception.WrongFormatException;

public final class WrongAccountNameFormat extends WrongFormatException {
    public WrongAccountNameFormat(String name) {
        super("WRONG_ACCOUNT_NAME_FORMAT", "The value \"" + name + "\" is not a valid AccountName.");
    }
}
