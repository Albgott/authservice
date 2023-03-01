package com.albgott.authservice.account.application.login;

import com.albgott.authservice.shared.application.Response;
import lombok.NonNull;

public record LoginResponse(@NonNull String token) implements Response {
}
