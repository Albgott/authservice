package com.albgott.authservice.account.domain.model;

import com.albgott.authservice.account.domain.exception.WrongAccountNameFormat;
import jakarta.persistence.Embeddable;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;

@Embeddable
public class AccountName {

    private String value;

    protected AccountName() {
    }

    public AccountName(String name){
        String formatedName = StringUtils.capitalize(name.trim());
        validate(formatedName);
        this.value = formatedName;
    }

    private void validate(String name){
        if(name.length() < 3)
            throw new WrongAccountNameFormat(name);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountName that = (AccountName) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
