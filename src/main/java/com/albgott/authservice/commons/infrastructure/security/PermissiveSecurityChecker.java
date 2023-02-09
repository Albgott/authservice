package com.albgott.authservice.commons.infrastructure.security;

import com.albgott.authservice.commons.domain.security.SecurityChecker;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PermissiveSecurityChecker implements SecurityChecker {
    public PermissiveSecurityChecker() {
    }


    @Override
    public SecurityChecker checkHasRole(String role) {
        return this;
    }

    @Override
    public SecurityChecker checkHasAnyRole(String[] roles) {
        return this;
    }

    @Override
    public SecurityChecker checkHasPermission(String permission) {
        return this;
    }

    @Override
    public SecurityChecker checkHasAnyPermission(String[] permissions) {
        return this;
    }

    @Override
    public SecurityChecker checkHasAllPermissions(String[] permissions) {
        return this;
    }

    @Override
    public SecurityChecker checkIsFromBusiness(UUID businessUuid) {
        return this;
    }

    @Override
    public SecurityChecker checkIsAuthenticated() {
        return this;
    }
}
