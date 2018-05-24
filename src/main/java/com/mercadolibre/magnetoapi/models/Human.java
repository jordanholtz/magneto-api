package com.mercadolibre.magnetoapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * A Human has a dna, that could contain strings of only A,T,G,C characters.
 */
@Entity
@Table(name = "humans")
public class Human {

    @Id
    @Column(name = "dna")
    private String[] dna;

    @NotNull
    @Column(name = "isMutant")
    private boolean isMutant;

    public Human() {
    }

    public Human(String[] dna, @NotNull boolean isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }
}
