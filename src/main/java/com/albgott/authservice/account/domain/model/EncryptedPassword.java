package com.albgott.authservice.account.domain.model;

import com.albgott.authservice.account.domain.exception.WrongPasswordFormat;
import jakarta.persistence.Embeddable;
import org.springframework.security.crypto.password.PasswordEncoder;

@Embeddable
public class EncryptedPassword {
    private String value;

    protected EncryptedPassword(){}

    public EncryptedPassword(String value) {
        this.value = value;
    }

    public EncryptedPassword(String password, PasswordEncoder encoder) {
        validate(password);
        this.value = encoder.encode(password);
    }

    private void validate(String password) {
        if (password.length() < 8) {
            throw new WrongPasswordFormat();
        }


    }

    public String value() {
        return value;
    }
}