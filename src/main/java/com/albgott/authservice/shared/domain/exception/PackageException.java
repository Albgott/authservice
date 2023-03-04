package com.albgott.authservice.shared.domain.exception;

import com.albgott.authservice.shared.application.AppErrors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PackageException extends RuntimeException {
    private List<String> errors = new ArrayList<>();

    public PackageException(Set<AppErrors> errors) {
        this.errors = errors.stream().map(AppErrors::errorCode).toList();
    }

    public List<String> errors() {
        return errors;
    }
}
