package com.albgott.authservice.account.application;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@ToString(of = {"address"})
public class EmailAddress {
    private static final String EMAIL_FORMAT = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|" +
            "(" + "([a-zA-Z0-9\\-]+\\" +".)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    String address;
    boolean verified;

    private EmailAddress(String address, boolean verified) {
        this.address = address;
        this.verified = verified;
    }

    public EmailAddress(String address) {
        ensureIsAnEmail(address);
        this.address = address.trim();
        this.verified = false;
    }

    private void ensureIsAnEmail(String email){
        if (!email.trim().matches(EMAIL_FORMAT))
            throw new RuntimeException("email have wrong format");
    }

    public EmailAddress verify(){
        return new EmailAddress(address, true);
    }
}
