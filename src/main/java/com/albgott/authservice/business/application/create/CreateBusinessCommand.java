package com.albgott.authservice.business.application.create;

import com.albgott.authservice.shared.application.Command;
import lombok.NonNull;

public record CreateBusinessCommand(
        @NonNull String businessId,
        @NonNull String businessName,
        @NonNull String accountId,
        @NonNull String accountName,
        @NonNull String email,
        @NonNull String password
) implements Command {
}
