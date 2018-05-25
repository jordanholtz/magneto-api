package com.mercadolibre.magnetoapi.service;

import com.mercadolibre.magnetoapi.controller.dtos.GetStatsResponse;
import com.mercadolibre.magnetoapi.controller.dtos.IsMutantRequest;

/**
 * Interface for mutant operations.
 */
public interface IMutantService {

    /**
     * Checks if a human's dna has a mutant gen.
     * @param input human dna.
     * @return mutant or not.
     */
    boolean isMutant(IsMutantRequest input);

    /**
     * Fetch stats about scanned humans.
     * @return stats.
     */
    GetStatsResponse getStats();
}
