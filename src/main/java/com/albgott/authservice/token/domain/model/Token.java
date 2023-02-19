package com.albgott.authservice.token.domain.model;

import com.albgott.authservice.account.domain.model.Account;
import com.albgott.authservice.shared.utils.DateUtils;
import jakarta.persistence.*;
import lombok.NonNull;
import org.apache.commons.lang.Validate;

import java.time.LocalDateTime;

@Entity(name = "tokens")
@Table(name = "tokens")
public class Token {
    @Id
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="value",column =@Column(name = "id"))
    })
    private TokenId id;

    @Enumerated(EnumType.STRING)
    private TokenType type;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "account_id", referencedColumnName = "id")
    })
    private Account account;
    @Column(nullable = false,name = "expires_at")
    private LocalDateTime expiresAt;

    protected Token(){}

    public Token(@NonNull TokenId id, @NonNull TokenType type, @NonNull Account account,
                 @NonNull LocalDateTime expiresAt) {
        this.id = id;
        this.type = type;
        this.account = account;
        this.expiresAt = expiresAt;
        Validate.isTrue(!hasExpired(), "Cannot create a token which has already expired");
    }



    public boolean hasExpired(){
        return DateUtils.hasDatePassed(expiresAt);
    }

    public Account account() {
        return account;
    }

    public String id() {
        return id.toString();
    }

    public boolean isVerificationToken() {
        return type.equals(TokenType.VERIFICATION_TOKEN);
    }
}
