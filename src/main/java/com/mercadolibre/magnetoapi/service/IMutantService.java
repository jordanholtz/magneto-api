package com.mercadolibre.magnetoapi.service;

import com.mercadolibre.magnetoapi.controllers.dtos.GetStatsResponse;
import com.mercadolibre.magnetoapi.controllers.dtos.IsMutantRequest;

public interface IMutantService {

    boolean isMutant(IsMutantRequest input);

    GetStatsResponse getStats();
}
