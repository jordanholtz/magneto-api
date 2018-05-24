package com.mercadolibre.magnetoapi.controllers;

import com.mercadolibre.magnetoapi.controllers.dtos.GetStatsResponse;
import com.mercadolibre.magnetoapi.controllers.dtos.IsMutantRequest;
import com.mercadolibre.magnetoapi.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping("/mutant")
    @ResponseBody
    public ResponseEntity<String> isMutant(@Valid @RequestBody IsMutantRequest input){

        HttpHeaders responseHeaders = new HttpHeaders();
        if(mutantService.isMutant(input)){
            return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, responseHeaders, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/stats")
    @ResponseBody
    public GetStatsResponse getStats(){
        return mutantService.getStats();
    }
}
