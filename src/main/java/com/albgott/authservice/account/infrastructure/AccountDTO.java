package com.albgott.authservice.account.infrastructure;

import com.albgott.authservice.account.domain.*;
import com.albgott.authservice.business.application.BusinessDTO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "account")
@Table(name = "accounts", uniqueConstraints = @UniqueConstraint(columnNames = {"email","business_id","role"}))
public class AccountDTO {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(name = "verified_email",nullable = false)
    private boolean verifiedEmail = false;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private BusinessDTO business;
    @Enumerated(EnumType.STRING)
    private AccountRole role;
    @Column(nullable = false, name = "created_at")
    private Timestamp createdAt;

    public Account fromThis(){
        return new Account(
                id,
                new AccountName(name),
                role,
                new EncryptedPassword(password),
                business.fromThis(),
                new EmailAddress(email, verifiedEmail),
                createdAt
        );
    }

    public static AccountDTO from(Account account){
        return new AccountDTO(
                account.getId(),
                account.getName().get(),
                account.getPassword().value(),
                account.getEmail().getAddress(),
                account.getEmail().isVerified(),
                BusinessDTO.from(account.getBusiness()),
                account.getRole(),
                account.getCreatedAt()
        );
    }
}
