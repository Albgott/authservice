package com.albgott.authservice.account.domain.model;

import com.albgott.authservice.account.domain.event.AccountVerifiedDomainEvent;
import com.albgott.authservice.business.domain.model.Business;
import com.albgott.authservice.shared.domain.model.AggregateRoot;
import com.albgott.authservice.shared.utils.DateUtils;
import jakarta.persistence.*;
import lombok.NonNull;
import org.apache.commons.lang.Validate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "accounts")
@Table(name = "accounts")
public class Account extends AggregateRoot {
    @Id
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="value",column =@Column(name = "id"))
    })
    private AccountID id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="accountName",column =@Column(name = "account_name",nullable = false)),
            @AttributeOverride(name="email",column =@Column(name = "email",nullable = false)),
            @AttributeOverride(name="verified",column =@Column(name = "verified",nullable = false)),
            @AttributeOverride(name="password",column =@Column(name = "password",nullable = false))
    })
    private Credentials credentials;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;
    @ElementCollection(fetch = FetchType.LAZY)

    @CollectionTable(name = "account_permission", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "permission_id")
    private Set<Permission> permissions = new HashSet<>();

    @Column(nullable = false,name = "role")
    @Enumerated(EnumType.STRING)
    private AccountRole role;

    @Column(nullable = false)
    private boolean enabled;
    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt = DateUtils.getCurrentDateTime();

    public Credentials credentials() {
        return credentials;
    }

    public Business business() {
        return business;
    }

    protected Account(){}

    public Account(@NonNull AccountID id, @NonNull Credentials credentials, @NonNull Business business,
                   @NonNull AccountRole role) {
        Validate.isTrue(!role.isSystem());
        this.id = id;
        this.credentials = credentials;
        this.business = business;
        this.role = role;
        this.enabled = true;
    }


    public Account(@NonNull AccountID id,@NonNull Credentials credentials,@NonNull AccountRole role) {
        Validate.isTrue(role.isSystem());
        this.id = id;
        this.credentials = credentials;
        this.role = role;
    }

    public AccountID id() {
        return id;
    }

    public Set<Permission> permissions() {
        return permissions;
    }

    public Set<String> permissionsNames() {
        return new HashSet<>();
    }

    public AccountRole role() {
        return role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public String password(){
        return credentials().password();
    }

    public String accountName(){
        return credentials().accountName();
    }

    public boolean isEmailVerified(){
        return credentials.isEmailVerified();
    }

    public String email(){
        return credentials.email();
    }

    public boolean canDoLogin(){
        return credentials.isEmailVerified() && isEnabled() && business().isEnabled();
    }

    public void verifyEmail() {
        if(isEmailVerified()) return;
        credentials.verifyEmail();
        record(new AccountVerifiedDomainEvent(id.toString()));
    }

    public String businessName(){
        if(business == null) return null;
        return business.getName().value();
    }

    public String businessId(){
        if(business == null) return null;
        return business.id().toString();
    }
}
