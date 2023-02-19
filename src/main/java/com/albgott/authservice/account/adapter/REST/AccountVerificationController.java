package com.albgott.authservice.account.adapter.REST;

import com.albgott.authservice.account.application.verify.VerifyAccountCommand;
import com.albgott.authservice.account.application.verify.VerifyAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts")
public class AccountVerificationController {

    private final VerifyAccountService verifyAccountService;

    public AccountVerificationController(VerifyAccountService verifyAccountService) {
        this.verifyAccountService = verifyAccountService;
    }

    @PutMapping("/{id}/verify-email")
    public ResponseEntity<String> verify(@PathVariable String id){
        verifyAccountService.exec(new VerifyAccountCommand(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
