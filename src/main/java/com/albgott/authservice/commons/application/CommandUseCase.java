package com.albgott.authservice.commons.application;

import jakarta.transaction.Transactional;

public interface CommandUseCase<R extends Request> {
    @Transactional
    void execute(R request);
}
