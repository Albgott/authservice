package com.albgott.authservice.business.application.create;

import com.albgott.authservice.shared.application.Command;
import lombok.NonNull;

public record CreateBusinessCommand(
        String businessId,
        String businessName,
        String accountId,
        String accountName,
        String email,
        String password
) implements Command {
}
