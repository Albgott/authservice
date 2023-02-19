package com.albgott.authservice.business.adapter.REST;

import com.albgott.authservice.business.application.create.CreateBusinessCommand;
import com.albgott.authservice.business.application.create.CreateBusinessService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/v1/business")
public class BusinessPostController {

    private final CreateBusinessService businessService;

    public BusinessPostController(CreateBusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping()
    public ResponseEntity<String> doPost(@RequestBody Request body){
        businessService.exec(new CreateBusinessCommand(
                body.businessId, body.businessName, body.accountId, body.accountName, body.email, body.password
        ));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private record Request(
            @NonNull String businessId,
            @NonNull String businessName,
            @NonNull String accountId,
            @NonNull String accountName,
            @NonNull String email,
            @NonNull String password
            ){}
}
