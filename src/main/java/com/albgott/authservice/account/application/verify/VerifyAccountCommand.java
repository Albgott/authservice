package com.albgott.authservice.account.application.verify;

import com.albgott.authservice.shared.application.Command;

public record VerifyAccountCommand(String tokenId) implements Command {
}
