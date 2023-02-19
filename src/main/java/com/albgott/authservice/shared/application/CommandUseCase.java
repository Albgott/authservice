package com.albgott.authservice.shared.application;

public interface CommandUseCase<C extends Command> {
    void exec(C command);
}
