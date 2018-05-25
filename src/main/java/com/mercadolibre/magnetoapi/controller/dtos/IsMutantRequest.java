package com.mercadolibre.magnetoapi.controller.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class IsMutantRequest {

    @NotNull
    @Size(min = 6,max = 6)
    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
