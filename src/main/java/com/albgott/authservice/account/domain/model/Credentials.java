package com.albgott.authservice.account.domain.model;

import jakarta.persistence.*;
import lombok.NonNull;

import java.util.Objects;

@Embeddable
public class Credentials {
    private AccountName accountName;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="address",column =@Column(name = "email")),
            @AttributeOverride(name="verified",column =@Column(name = "email_verified"))
    })
    private Email email;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="value",column =@Column(name = "password"))
    })
    private EncryptedPassword password;

    protected Credentials(){}

    public Credentials(@NonNull AccountName accountName, @NonNull Email email, @NonNull EncryptedPassword password) {
        this.accountName = accountName;
        this.email = email;
        this.password = password;
    }

    public String accountName(){
        return accountName.value();
    }

    public String email(){
        return email.address();
    }

    public String password(){
        return password.value();
    }

    public boolean isEmailVerified(){
        return email.isVerified();
    }

    public void verifyEmail(){
        this.email = email.verify();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credentials that = (Credentials) o;
        return Objects.equals(accountName, that.accountName) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountName, email, password);
    }
}
