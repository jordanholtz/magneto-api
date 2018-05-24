package com.mercadolibre.magnetoapi.service;

import com.mercadolibre.magnetoapi.controllers.dtos.GetStatsResponse;
import com.mercadolibre.magnetoapi.controllers.dtos.IsMutantRequest;
import com.mercadolibre.magnetoapi.facade.MutantDao;
import com.mercadolibre.magnetoapi.helpers.MutantHelper;
import com.mercadolibre.magnetoapi.models.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MutantService implements IMutantService {

    private static final String REGEX = "^(A|T|G|C){6}$";

    @Autowired
    private MutantDao mutantFacade;

    @Override
    public boolean isMutant(IsMutantRequest input){
        String[] dna = input.getDna();
        String line = dna[0];
        Pattern pattern = Pattern.compile(REGEX);

        // Now create matcher object.
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()){
            throw new RuntimeException("Dna sequences must contain only A, T, G or C characters.");

        }

        boolean response = MutantHelper.checkDna(dna);
        Human humanScanned = new Human();
        humanScanned.setDna(input.getDna());
        humanScanned.setMutant(response);
        mutantFacade.save(humanScanned);

        return response;
    }

    @Override
    public GetStatsResponse getStats() {
        GetStatsResponse response = new GetStatsResponse();
        response.setCount_human_dna(mutantFacade.findAll().size());
        response.setCount_mutant_dna(mutantFacade.fetchHumans(false).size());
        response.setRatio(response.getCount_mutant_dna() == 0 ?
                1.0f : (float) response.getCount_mutant_dna() / response.getCount_human_dna());
        return response;
    }
}
