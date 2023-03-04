package com.albgott.authservice.account.adapter.REST;

import com.albgott.authservice.account.application.login.LoginQuery;
import com.albgott.authservice.account.application.login.LoginResponse;
import com.albgott.authservice.account.application.login.LoginService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class BusinessAuthController {

    private final LoginService loginService;

    public BusinessAuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> doLogin(@RequestBody Body body){
        LoginResponse response = loginService.exec(new LoginQuery(body.email,body.password,body.businessName,false));
        return ResponseEntity.ok(response);
    }

    private record Body(String businessName,String email,String password) {}
}
