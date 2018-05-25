package com.mercadolibre.magnetoapi.controller.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

public class IsMutantResponse extends ResourceSupport {

    @JsonIgnore
    private boolean isMutant;

    @JsonIgnore
    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }
}
