package com.albgott.authservice.account.domain.model;

import com.albgott.authservice.account.domain.exception.WrongPermissionNameFormat;
import jakarta.persistence.Embeddable;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;

@Embeddable
public class Permission {
    private String name;

    protected Permission(){}

    public Permission(String name) {
        if(StringUtils.isEmpty(name.trim()))
            throw new WrongPermissionNameFormat(name);
        this.name = name.trim();
    }

    public String value() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
