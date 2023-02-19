package com.albgott.authservice.business.application.create;

import com.albgott.authservice.shared.domain.security.SecurityService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CreateBusinessServiceProxy implements CreateBusinessService {

    private final CreateBusinessServiceImpl service;
    private final SecurityService securityService;

    public CreateBusinessServiceProxy(CreateBusinessServiceImpl service, SecurityService securityService) {
        this.service = service;
        this.securityService = securityService;
    }

    @Override
    public void exec(CreateBusinessCommand command) {
        service.exec(command);
    }
}
