package com.albgott.authservice.account.domain.service;

import com.albgott.authservice.account.domain.model.Account;
import com.albgott.authservice.account.domain.model.AccountDetails;
import com.albgott.authservice.account.domain.model.Email;
import com.albgott.authservice.account.domain.repository.AccountRepository;
import com.albgott.authservice.business.domain.model.Business;
import com.albgott.authservice.business.domain.model.BusinessName;
import com.albgott.authservice.business.domain.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private BusinessRepository businessRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] fields = username.split("\\|",3);
        String email = fields[0];
        String businessName = fields[1];
        String role = fields[2];

        if(email.equals("")){
            throw new UsernameNotFoundException("Invalid username format");
        }

        Account account;
        if(businessName.equals("")){
            account = repository.findSystemAccount(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return new AccountDetails(account);
        }

        Business business = businessRepository.findByName(new BusinessName(businessName))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(role.equalsIgnoreCase("BUSINESS")){
            account = repository.findBusinessWorkerAccount(email, business)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return new AccountDetails(account);
        }

        account = repository.findBusinessClientAccount(email, business)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new AccountDetails(account);
    }
}
