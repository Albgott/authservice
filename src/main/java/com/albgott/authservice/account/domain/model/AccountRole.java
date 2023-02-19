package com.albgott.authservice.account.domain.model;

public enum AccountRole {
    ADMIN, BUSINESS_OWNER,BUSINESS_EMPLOYEE, BUSINESS_CLIENT;

    public boolean isSystem(){
        return this.equals(ADMIN);
    }

    public boolean isClient(){
        return this.equals(BUSINESS_CLIENT);
    }

    public boolean isBusiness(){
        return this.equals(BUSINESS_EMPLOYEE) || this.equals(BUSINESS_OWNER);
    }
}
