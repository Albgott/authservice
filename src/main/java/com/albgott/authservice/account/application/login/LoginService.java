package com.albgott.authservice.account.application.login;

import com.albgott.authservice.account.domain.model.Account;
import com.albgott.authservice.account.domain.repository.AccountRepository;
import com.albgott.authservice.business.domain.model.Business;
import com.albgott.authservice.business.domain.model.BusinessName;
import com.albgott.authservice.business.domain.repository.BusinessRepository;
import com.albgott.authservice.shared.application.QueryUseCase;
import com.albgott.authservice.shared.domain.exception.UnauthorizedException;
import com.albgott.authservice.shared.utils.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements QueryUseCase<LoginQuery, LoginResponse> {

    private final AccountRepository accountRepository;
    private final BusinessRepository businessRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public LoginService(AccountRepository accountRepository, BusinessRepository businessRepository,
                        PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.accountRepository = accountRepository;
        this.businessRepository = businessRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public LoginResponse exec(LoginQuery query) {
        Account account = getAccountFromQuery(query);
        String token = getTokenIfPossible(account, query.password());
        return new LoginResponse(token);
    }

    private Account getAccountFromQuery(LoginQuery query) {
        String email = query.email().trim();
        String businessName = query.businessName() != null? query.businessName().trim() : "";
        boolean isClient = query.isClient();

        if(isSystemLogin(businessName))
            return getSystemAccount(email);

        Business business = getBusiness(businessName);
        if(isClient)
            return getClientAccount(email, business);

        return getBusinessWorkerAccount(email,business);
    }

    private String getTokenIfPossible(Account account, String password) {
        if(!account.canDoLogin())
            throw new UnauthorizedException("UNABLE_TO_DO_LOGIN","");
        if(!encoder.matches(password, account.password()))
            throw new BadCredentialsException("Bad credentials");

        return jwtUtils.from(account);
    }

    private boolean isSystemLogin(String businessName){
        return businessName.equals("");
    }

    private Account getSystemAccount(String email){
        return accountRepository.findSystemAccount(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Account getClientAccount(String email, Business business){
        return accountRepository.findBusinessClientAccount(email, business)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Account getBusinessWorkerAccount(String email, Business business){
        return accountRepository.findBusinessWorkerAccount(email, business)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Business getBusiness(String businessName) {
        return businessRepository.findByName(new BusinessName(businessName))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
