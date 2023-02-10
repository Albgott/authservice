package com.albgott.authservice.account.application;

import com.albgott.authservice.business.domain.Business;
import com.albgott.authservice.commons.domain.AggregateRoot;

import java.sql.Timestamp;
import java.util.UUID;

public class Account extends AggregateRoot {
    private UUID id;
    private AccountName name;
    private AccountType role;
    private Business business;
    private EmailAddress email;
    private Timestamp createdAt;

}
