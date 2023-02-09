package com.albgott.authservice.commons.domain.security;

import java.util.UUID;

public interface SecurityChecker {
    SecurityChecker checkHasRole(String role);
    SecurityChecker checkHasAnyRole(String[] roles);
    SecurityChecker checkHasPermission(String permission);
    SecurityChecker checkHasAnyPermission(String[] permissions);
    SecurityChecker checkHasAllPermissions(String[] permissions);
    SecurityChecker checkIsFromBusiness(UUID businessUuid);
    SecurityChecker checkIsAuthenticated();
}
