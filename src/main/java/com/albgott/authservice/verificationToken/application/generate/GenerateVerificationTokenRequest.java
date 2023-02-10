package com.albgott.authservice.verificationToken.application.generate;

import com.albgott.authservice.commons.application.Request;

import java.util.UUID;

public record GenerateVerificationTokenRequest(UUID accountId) implements Request {
}
