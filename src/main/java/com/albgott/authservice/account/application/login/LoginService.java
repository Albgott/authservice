package com.albgott.authservice.account.application.login;

import com.albgott.authservice.account.domain.model.Account;
import com.albgott.authservice.account.domain.repository.AccountRepository;
import com.albgott.authservice.business.domain.model.Business;
import com.albgott.authservice.business.domain.model.BusinessName;
import com.albgott.authservice.business.domain.repository.BusinessRepository;
import com.albgott.authservice.shared.application.AppErrors;
import com.albgott.authservice.shared.application.QueryUseCase;
import com.albgott.authservice.shared.domain.exception.PackageException;
import com.albgott.authservice.shared.domain.exception.UnauthorizedException;
import com.albgott.authservice.shared.utils.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LoginService implements QueryUseCase<LoginQuery, LoginResponse> {
    private final Set<AppErrors> serviceErrors = new HashSet<>();

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
        validateQuery(query);

        String token = "";
        try {
            Account account = getAccountFromQuery(query);
            token = getTokenIfPossible(account, query.password());
        } catch (Exception e) {
            throw e;
        }
        return new LoginResponse(token);
    }

    private void validateQuery(LoginQuery query) {
        if(query.businessName() == null) serviceErrors.add(AppErrors.BUSINESSNAME_REQUIRED);
        if(query.password() == null) serviceErrors.add(AppErrors.PASSWORD_REQUIRED);
        if(query.email() == null) serviceErrors.add(AppErrors.EMAIL_REQUIRED);
        throwIfErrors();
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
        if(!account.canDoLogin()) {
            serviceErrors.add(AppErrors.USER_NOT_VERIFIED);
            throwIfErrors();
            throw new UnauthorizedException("UNABLE_TO_DO_LOGIN","");
        }
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

    private void throwIfErrors(){
        if(!serviceErrors.isEmpty()){
            throw new PackageException(serviceErrors);
        }
    }
}
