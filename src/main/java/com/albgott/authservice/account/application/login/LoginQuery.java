package com.albgott.authservice.account.application.login;

import com.albgott.authservice.shared.application.Query;
import lombok.NonNull;

public record LoginQuery(String email,String password, String businessName, boolean isClient) implements Query {
}
