package com.albgott.authservice.account.domain.exception;

import com.albgott.authservice.shared.domain.exception.WrongFormatException;

public final class WrongAccountIdFormat extends WrongFormatException {

    public WrongAccountIdFormat(String accountId) {
        super("WRONG_ACCOUNT_ID_FORMAT", "The value \"" + accountId + "\" is not a valid AccountId format.");
    }
}
