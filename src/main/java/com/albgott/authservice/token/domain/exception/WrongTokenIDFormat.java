package com.albgott.authservice.token.domain.exception;

import com.albgott.authservice.shared.domain.exception.WrongFormatException;

public class WrongTokenIDFormat extends WrongFormatException {

    public WrongTokenIDFormat(String id) {
        super("WRONG_TOKEN_ID_FORMAT", "The value \"" + id + "\" is not a valid tokenId format.");
    }
}
