package com.albgott.authservice.account.domain;

import lombok.Value;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public record Password(String value) {
    public Password(String value) {
        ensureHaveMoreThan5Characters(value);
        this.value = value;
    }

    private void ensureHaveMoreThan5Characters(String value) {
        if(StringUtils.length(value) < 6)
            throw new RuntimeException("Weak password");
    }

    public EncryptedPassword encrypt(PasswordEncoder encoder){
        return new EncryptedPassword(encoder.encode(value));
    }
}
