package com.mercadolibre.magnetoapi.controller;

import com.mercadolibre.magnetoapi.controller.dtos.GetStatsResponse;
import com.mercadolibre.magnetoapi.controller.dtos.IsMutantRequest;
import com.mercadolibre.magnetoapi.controller.dtos.IsMutantResponse;
import com.mercadolibre.magnetoapi.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class MutantController {

    @Autowired
    private MutantService mutantService;

    /**
     * Checks if it's a dna mutant or not.
     * @param input dna
     * @return OK if it's mutant, FORBIDDEN if it's not.
     */
    @PostMapping("/mutant")
    @ResponseBody
    public ResponseEntity<IsMutantResponse> isMutant(@Valid @RequestBody IsMutantRequest input){

        HttpHeaders responseHeaders = new HttpHeaders();
        IsMutantResponse response = new IsMutantResponse();

        response.setMutant(mutantService.isMutant(input));

        response.add(linkTo(methodOn(MutantController.class).isMutant(input)).withSelfRel());
        response.add(linkTo(methodOn(MutantController.class).getStats()).withRel("stats"));
        if(response.isMutant()){
            return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, responseHeaders, HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Return statistics about mutants scanned.
     * @return stats.
     */
    @GetMapping("/stats")
    @ResponseBody
    public GetStatsResponse getStats(){
        return mutantService.getStats();
    }
}
