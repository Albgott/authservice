package com.albgott.authservice.account.domain.exception;

import com.albgott.authservice.shared.domain.exception.WrongFormatException;

public final class WrongPermissionNameFormat extends WrongFormatException {
    public WrongPermissionNameFormat(String name) {
        super("WRONG_PERMISSION_NAME_FORMAT", "The value \"" + name + "\" is not a valid PermissionName.");
    }
}
