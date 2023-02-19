package com.albgott.authservice.account.application.login;

import com.albgott.authservice.shared.application.Response;

public record LoginResponse(String token) implements Response {
}
