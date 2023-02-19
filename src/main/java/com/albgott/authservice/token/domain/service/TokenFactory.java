package com.albgott.authservice.token.domain.service;

import com.albgott.authservice.account.domain.model.Account;
import com.albgott.authservice.shared.utils.DateUtils;
import com.albgott.authservice.token.domain.model.Token;
import com.albgott.authservice.token.domain.model.TokenId;
import com.albgott.authservice.token.domain.model.TokenType;

import java.util.UUID;

public class TokenFactory {
    public Token generateVerificationToken(Account account){
        return new Token(new TokenId(UUID.randomUUID().toString()), TokenType.VERIFICATION_TOKEN,account,
                DateUtils.getCurrentDateTimePlusHours(36));
    }
}
