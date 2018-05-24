package com.mercadolibre.magnetoapi.helpers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MutantHelper {

    public static boolean checkDna(String[] dnaSecuence){
        char [][] dnaMatrix = createDnaMatrix(dnaSecuence);

        boolean res = false;
        for (int i = 0; i < 6 && !res; i++) {
            for (int j = 0; j < 6 && !res; j++) {

                    /*try {
                        res = checkMatchAsync(i,j,dnaMatrix);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    res = checkVerticalMatch(i, j, dnaMatrix) ||
                            checkHorizontalMatch(i, j, dnaMatrix) ||
                            checkDiagonalMatch(i, j, dnaMatrix);

            }
        }
        return res;
    }

    public static boolean checkMatchAsync(int i, int j, char [][] dnaMatrix) throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<Boolean>> callables = Arrays.asList(
                () -> checkVerticalMatch(i, j, dnaMatrix),
                () -> checkHorizontalMatch(i, j, dnaMatrix),
                () -> checkDiagonalMatch(i, j, dnaMatrix));

        return executor.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        boolean var = future.get();
                        System.out.println(var);
                        return var;
                    }
                    catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .reduce(false, Boolean::logicalOr);
    }

    private static char[][] createDnaMatrix(String[] dnaSequence) {

        char[][] dnaMatrix = new char[6][6];
        int index = 0;
        for (String dna : dnaSequence) {
            char[] dnaArray = dna.toCharArray();

            insertIntoMatrix(dnaArray, dnaMatrix, index);

            index++;
        }

        return dnaMatrix;
    }

    private static void insertIntoMatrix(char[] dnaArray, char[][] dnaMatrix, int index) throws RuntimeException {
        if (dnaArray.length != 6) {
            throw new RuntimeException("Invalid dna length. Must be 6.");
        }

        for(int i = 0; i<6 ; i++ ){
            dnaMatrix[index][i] = dnaArray[i];
        }
    }

    private static boolean checkDiagonalMatch(int x, int y, char[][] dnaMatrix) throws RuntimeException {
        if(x>5 || y>5) {
            throw new RuntimeException("Invalid dna length. Must be 6.");
        }

        if(x+3<6 && y+3<6){
            char dnaChar = dnaMatrix[x][y];
            for (int i=1; i<=3; i++){
                if(dnaMatrix[x+i][y+i]!=dnaChar){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean checkHorizontalMatch(int x, int y, char[][] dnaMatrix) throws RuntimeException {
        if(x>5 || y>5) {
            throw new RuntimeException("Invalid dna length. Must be 6.");
        }

        if(y+3<6){
            char dnaChar = dnaMatrix[x][y];
            for (int i=y+1; i<=y+3; i++){
                if(dnaMatrix[x][i]!=dnaChar){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean checkVerticalMatch(int x, int y, char[][] dnaMatrix) throws RuntimeException {
        if(x>5 || y>5) {
            throw new RuntimeException("Invalid dna length. Must be 6.");
        }

        if(x+3<6){
            char dnaChar = dnaMatrix[x][y];
            for (int i=x+1; i<=x+3; i++){
                if(dnaMatrix[i][y]!=dnaChar){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
