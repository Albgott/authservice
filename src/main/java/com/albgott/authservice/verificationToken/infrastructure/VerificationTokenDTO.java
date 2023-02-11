package com.albgott.authservice.verificationToken.infrastructure;

import com.albgott.authservice.account.infrastructure.AccountDTO;
import com.albgott.authservice.verificationToken.domain.VerificationToken;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "verificationToken")
@Table(name = "verification_tokens")
public class VerificationTokenDTO {
    @Id
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountDTO account;
    @Column(name = "expires_at", nullable = false)
    private Timestamp expiresAt;

    public VerificationToken fromThis(){
        return new VerificationToken(
                id,
                account.fromThis(),
                expiresAt
        );
    }

    public static VerificationTokenDTO from(VerificationToken token){
        return new VerificationTokenDTO(token.getId(),AccountDTO.from(token.getAccount()),token.getExpiresAt());
    }
}
