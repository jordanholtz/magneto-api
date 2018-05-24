package com.mercadolibre.magnetoapi.helpers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MutantHelperTest {

    private static final String[] diagonalCheckDna = new String[]{"ATGCTA","CAGTGC","TTATGT","AGAACG","CCCCTA","TCACTG"};
    private static final String[] verticalCheckDna = new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    private static final String[] horizontalCheckDna = new String[]{"ATGCGA","CAGTGC","TTATGT","AGAACG","CCCCTA","TCACTG"};
    private static final String[] noCheckDna = new String[]{"CTGCGA","CAGTGC","TTATGT","AGAACG","CCCTTA","TCACTG"};

    @Test
    public void whenDnaWithDiagonalCheckProvided_thenIsMutant() {
        boolean isMutantResult = MutantHelper.checkDna(diagonalCheckDna);
        Assert.assertEquals(true, isMutantResult);
    }

    @Test
    public void whenDnaWithVerticalCheckProvided_thenIsMutant() {
        boolean isMutantResult = MutantHelper.checkDna(verticalCheckDna);
        Assert.assertEquals(true, isMutantResult);
    }

    @Test
    public void whenDnaWithHorizontalCheckProvided_thenIsMutant() {
        boolean isMutantResult = MutantHelper.checkDna(horizontalCheckDna);
        Assert.assertEquals(true, isMutantResult);
    }

    @Test
    public void whenDnaWithNoCheckProvided_thenIsMutant() {
        boolean isMutantResult = MutantHelper.checkDna(noCheckDna);
        Assert.assertEquals(false, isMutantResult);
    }
}
