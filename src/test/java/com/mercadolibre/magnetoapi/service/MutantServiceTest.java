package com.mercadolibre.magnetoapi.service;

import com.mercadolibre.magnetoapi.controller.dtos.GetStatsResponse;
import com.mercadolibre.magnetoapi.controller.dtos.IsMutantRequest;
import com.mercadolibre.magnetoapi.model.Human;
import com.mercadolibre.magnetoapi.repository.MutantDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class MutantServiceTest {

    private static final String[] invalidDna = new String[]{"ATGCGX","CAGTGC","TTATGT","AGAACG","CCCCTA","TCACTG"};
    private static final String[] validDna = new String[]{"CTGCGA","CAGTGC","TTATGT","AGAACG","CCCTTA","TCACTG"};
    private static List<Human> allHumans = Arrays.asList(
            new Human(new String[]{"ATGCGA","ATGCGA","ATGCGA","ATGCGA","ATGCGA","ATGCGA"}, true),
            new Human(new String[]{"ATGCGA","ATGCGA","ATGCGA","ATGCGA","ATGCGA","ATGCGC"}, true),
            new Human(new String[]{"ATGCGA","ATGCGA","ATGCGA","ATGCGA","ATGCGA","ATGCGG"}, true),
            new Human(new String[]{"ATGCGA","ATGCGA","ATGCGA","ATGCGA","ATGCGA","ATGCGT"}, true),
            new Human(new String[]{"ATGCGX","CAGTGC","TTATGT","AGAACG","CCCCTA","TCACTG"}, false)
    );

    @Mock
    private MutantDao dao;

    @InjectMocks
    private MutantService service;

    @Test(expected = IllegalArgumentException.class)
    public void whenDnaWithInvalidRegexProvided_thenThrowException() {
        IsMutantRequest request = new IsMutantRequest();
        request.setDna(invalidDna);
        service.isMutant(request);

        Mockito.verify(dao, times(0)).save(Mockito.any(Human.class));
    }

    @Test
    public void whenDnaWithValidRegexProvided_thenDnaSaved() {
        IsMutantRequest request = new IsMutantRequest();
        request.setDna(validDna);
        service.isMutant(request);

        Mockito.verify(dao).save(Mockito.any(Human.class));
    }

    @Test
    public void whenGetStats_thenFetchedStatsReturned() {
        Mockito.when(dao.fetchHumans(Mockito.anyBoolean())).thenReturn(new ArrayList<>());
        GetStatsResponse statsResponse = service.getStats();

        Mockito.verify(dao).findAll();
        Mockito.verify(dao).fetchHumans(Mockito.anyBoolean());
        Assert.assertNotNull(statsResponse);
    }

    @Test
    public void whenGetStats_thenRatioIsCalculatedCorrectly() {
        List<Human> onlyMutants = allHumans.stream().filter(h -> h.isMutant()).collect(Collectors.toList());
        Mockito.when(dao.fetchHumans(true)).thenReturn(onlyMutants);
        Mockito.when(dao.findAll()).thenReturn(allHumans);
        GetStatsResponse statsResponse = service.getStats();

        Assert.assertEquals(0.8f,statsResponse.getRatio(), 0.0001f);
    }

}
