package com.albgott.authservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Value("${jwt.signing.key}")
    private String signingKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");
        if(!StringUtils.isEmpty(jwt)) {
            SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            String name = String.valueOf(claims.get("name"));
            List<GrantedAuthority> permissions = ((ArrayList<String>) claims.get("permissions"))
                    .stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());


            var authentication = new UsernamePasswordAuthenticationToken(name, null, permissions);
            authentication.setDetails(new HashMap<>() {{
                put("id", String.valueOf(claims.get("role")));
                put("name", String.valueOf(claims.get("name")));
                put("role", String.valueOf(claims.get("role")));
                put("business_id", String.valueOf(claims.get("business_id")));
                put("business_name", String.valueOf(claims.get("business_name")));
                put("permissions", String.valueOf(claims.get("permissions")));
            }});

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        var currentAuth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("New Authentication object: " + currentAuth);
        filterChain.doFilter(request, response);
    }

}
