package com.albgott.authservice.business.domain.exception;

import com.albgott.authservice.shared.domain.exception.BadParamException;

public final class BusinessNameAlreadyOnUse extends BadParamException {
    public BusinessNameAlreadyOnUse(String name) {
        super("BUSINESS_NAME_ON_USE", "The businessName \"" + name + "\" is already on use.");
    }
}
