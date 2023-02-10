package com.albgott.authservice.account.application;

import com.albgott.authservice.commons.domain.Utils;
import lombok.Value;

@Value
public class AccountName {
    String value;
    public AccountName(String username) {
        username = Utils.clean(username);
        ensureIsAtLeast3CharactersLong(username);
        ensureHasNoEspecialCharacters(username);
        this.value = username;
    }

    public String get(){
        return value;
    }

    private void ensureIsAtLeast3CharactersLong(String name) {
        if(name.length() < 3)
            throw new RuntimeException("accountName too short");
    }

    private void ensureHasNoEspecialCharacters(String name) {
        if(!name.matches("^[a-zA-Z0-9_äöüÄÖÜ\\s]*$"))
            throw new RuntimeException("accountName cannot have special characters");;
    }
}
