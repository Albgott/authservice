package com.albgott.authservice.business.domain.model;

import com.albgott.authservice.business.domain.exception.WrongBusinessNameFormat;

import java.util.Objects;


public class BusinessName {
    private final String value;

    public BusinessName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new WrongBusinessNameFormat(name);
        }
        this.value = name.trim().toUpperCase();
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessName that = (BusinessName) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
