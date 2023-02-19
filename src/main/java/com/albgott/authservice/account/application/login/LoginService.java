package com.albgott.authservice.account.application.login;

import com.albgott.authservice.shared.application.QueryUseCase;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements QueryUseCase<LoginQuery, LoginResponse> {



    @Override
    public LoginResponse exec(LoginQuery query) {
        String email = query.email();
        String password = query.password();
        String businessName = query.businessName() != null? query.businessName() : "";


        return null;
    }
}
