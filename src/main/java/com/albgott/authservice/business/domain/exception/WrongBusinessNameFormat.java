package com.albgott.authservice.business.domain.exception;

import com.albgott.authservice.shared.domain.exception.WrongFormatException;

public final class WrongBusinessNameFormat extends WrongFormatException {
    public WrongBusinessNameFormat(String businessName) {
        super("WRONG_BUSINESS_NAME_FORMAT", "The value \"" + businessName + "\" is not a valid BusinessName format.");
    }
}
