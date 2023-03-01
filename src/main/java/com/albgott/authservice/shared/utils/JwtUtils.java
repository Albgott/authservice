package com.albgott.authservice.shared.utils;

import com.albgott.authservice.account.domain.model.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.ZoneOffset;
import java.util.HashMap;

@Service
public class JwtUtils {
    @Value("${jwt.key}")
    private String signingKey;

    public String from(Account account){
        SecretKey key = Keys.hmacShaKeyFor(
                signingKey.getBytes(StandardCharsets.UTF_8)
        );

        String jwt = Jwts.builder()
                .setClaims(new HashMap<>(){{
                    put("business_id", account.businessId());
                    put("business_name", account.businessName());
                    put("account_name", account.accountName());
                    put("account_id", account.id().toString());
                    put("account_role",account.role().toString());
                    put("permissions",account.permissionsNames());
                    put("email", account.email());
                }})
                .signWith(key)
                .setExpiration(Date.from(DateUtils.getCurrentDateTimePlusHours(24).toInstant(ZoneOffset.ofHours(0))))
                .compact();

        return jwt;
    }
}
