package com.albgott.authservice.business.controller;

import com.albgott.authservice.business.application.create.CreateBusinessRequest;
import com.albgott.authservice.business.application.create.CreateBusinessUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class BusinessPostController {

    @Autowired
    private CreateBusinessUseCase createBusinessUseCase;

    @PostMapping("/business")
    public ResponseEntity<String> createBusiness(@RequestBody Body body){
        createBusinessUseCase.execute(new CreateBusinessRequest(
                body.accountId,
                body.accountName,
                body.password,
                body.email,
                body.businessId,
                body.businessName
        ));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    record Body(UUID accountId, String accountName, String email, String password, UUID businessId,
                String businessName) {
    }
}
