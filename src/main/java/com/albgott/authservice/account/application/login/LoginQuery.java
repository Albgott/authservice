package com.albgott.authservice.account.application.login;

import com.albgott.authservice.shared.application.Query;
import lombok.NonNull;

public record LoginQuery(@NonNull String email,@NonNull String password, String businessName) implements Query {
}
