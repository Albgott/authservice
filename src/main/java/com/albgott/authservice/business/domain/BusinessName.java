package com.albgott.authservice.business.domain;

import com.albgott.authservice.commons.domain.Utils;
import lombok.Value;

@Value
public class BusinessName {
    String value;

    public BusinessName(String name){
        name = Utils.clean(name);
        ensureHasNoEspecialCharacters(name);
        ensureIsAtLeast5CharactersLong(name);
        this.value = name;
    }

    private void ensureIsAtLeast5CharactersLong(String name) {
        if(name.trim().length() < 5)
            throw new RuntimeException("businessName is too small");
    }

    private void ensureHasNoEspecialCharacters(String name) {
        if(!name.matches("^[a-zA-Z0-9_äöüÄÖÜ\\s]*$"))
            throw new RuntimeException("businessName cannot have special characters");
    }

}
