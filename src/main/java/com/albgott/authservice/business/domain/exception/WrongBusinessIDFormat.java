package com.albgott.authservice.business.domain.exception;

import com.albgott.authservice.shared.domain.exception.WrongFormatException;

public final class WrongBusinessIDFormat extends WrongFormatException {

    public WrongBusinessIDFormat(String businessId) {
        super("WRONG_BUSINESS_ID_FORMAT", "The value \"" + businessId + "\" is not a valid BusinessId format.");
    }
}