package com.mercadolibre.magnetoapi.controller;

import com.mercadolibre.magnetoapi.controller.dtos.IsMutantResponse;
import com.mercadolibre.magnetoapi.service.MutantService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RunWith(MockitoJUnitRunner.class)
public class MutantControllerTest {

    @Mock
    private MutantService service;

    @InjectMocks
    private MutantController controller;

    @Test
    public void whenMutantDnaProvided_thenReturnOk() {
        Mockito.when(service.isMutant(Mockito.any())).thenReturn(true);
        ResponseEntity<IsMutantResponse> controllerResult = controller.isMutant(Mockito.any());
        Assert.assertEquals(HttpStatus.OK, controllerResult.getStatusCode());
    }

    @Test
    public void whenHumanDnaProvided_thenReturnForbidden() {
        Mockito.when(service.isMutant(Mockito.any())).thenReturn(false);
        ResponseEntity<IsMutantResponse> controllerResult = controller.isMutant(Mockito.any());
        Assert.assertEquals(HttpStatus.FORBIDDEN, controllerResult.getStatusCode());
    }
}
