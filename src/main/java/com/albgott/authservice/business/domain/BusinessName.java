package com.albgott.authservice.account.infrastructure;

import lombok.Value;

@Value
public class BusinessName {
    private String value;

    

    private void ensureIsAtLeast5CharactersLong(String name) {
        assert !(name.trim().length() < 5);
    }

    private void ensureHasNoEspecialCharacters(String name) {
        assert name.matches("^[a-zA-Z0-9_äöüÄÖÜ\\s]*$");
    }

}
