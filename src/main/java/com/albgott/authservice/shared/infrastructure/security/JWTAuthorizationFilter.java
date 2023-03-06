package com.albgott.authservice.shared.infrastructure.security;

import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Value("${jwt.key}")
    private String jwtKey;
    private final String HEADER = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
try{
        if(!requestIncludesToken(request)){
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
            return;
        }

        Claims claims = getClaimsFromToken(request);
        setUpSpringAuthentication(claims);
        filterChain.doFilter(request, response);

    } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        return;
    }
    }

    private void setUpSpringAuthentication(Claims claims) {
        List<String> authorities = (List<String>) claims.get("permissions");
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(),null,
                authorities.stream().map(SimpleGrantedAuthority::new).toList());
        auth.setDetails(claims);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private boolean requestIncludesToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER);
        return authenticationHeader != null;
    }

    private Claims getClaimsFromToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER);
        return Jwts.parser().setSigningKey(jwtKey.getBytes()).parseClaimsJws(jwtToken).getBody();
    }
}
