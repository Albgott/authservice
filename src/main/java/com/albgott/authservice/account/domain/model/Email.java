package com.albgott.authservice.account.domain.model;

import com.albgott.authservice.account.domain.exception.WrongEmailFormat;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Email {
    private String address;
    private boolean verified;

    protected Email(){}

    private Email(String address, boolean verified) {
        this.address = address;
        this.verified = verified;
        hasValidEmailFormat(address);
    }

    public Email(String address) {
        this(address, false);
    }

    private void hasValidEmailFormat(String email) {
        String mailFormat = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if(!email.matches(mailFormat))
            throw new WrongEmailFormat(email);

    }

    public String address() {
        return address;
    }

    public boolean isVerified() {
        return verified;
    }

    public Email verify(){
        return new Email(address,true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
