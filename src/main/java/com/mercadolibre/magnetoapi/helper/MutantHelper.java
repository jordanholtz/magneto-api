package com.mercadolibre.magnetoapi.helper;

/**
 * Helper to determine if a human has the mutant gene.
 */
public class MutantHelper {

    private static final byte DNA_ROWS = 6;
    private static final byte DNA_COLUMNS = 6;

    /**
     * Check if a Dna has a mutant gene.
     * @param dnaSecuence Dna secuence.
     * @return has mutant gene.
     */
    public static boolean checkDna(String[] dnaSecuence) {
        char[][] dnaMatrix = createDnaMatrix(dnaSecuence);

        boolean res = false;
        for (int i = 0; i < DNA_ROWS && !res; i++) {
            for (int j = 0; j < DNA_COLUMNS && !res; j++) {
                res = checkAll(i,j,dnaMatrix);
            }
        }
        return res;
    }

    private static char[][] createDnaMatrix(String[] dnaSequence) {

        char[][] dnaMatrix = new char[DNA_ROWS][DNA_COLUMNS];
        int index = 0;
        for (String dna : dnaSequence) {
            char[] gene = dna.toCharArray();

            insertIntoMatrix(gene, dnaMatrix, index);

            index++;
        }

        return dnaMatrix;
    }

    /**
     * Inserts a gene into a position of the dna matrix.
     * @param gene gene to insert into the matrix. Example: [A,T,T,T,G,A]
     * @param dnaMatrix destination dna matrix.
     * @param index position of insertion.
     */
    private static void insertIntoMatrix(char[] gene, char[][] dnaMatrix, int index) {
        if (gene.length != 6) {
            throw new IllegalArgumentException("Invalid dna length. Must be 6.");
        }

        System.arraycopy(gene, 0, dnaMatrix[index], 0, 6);
    }

    /**
     * Checks all possibles matches for a specific position in the dna matrix.
     * @param x initial x component
     * @param y initial y component
     * @param dnaMatrix dna matrix to check.
     * @return true if has at least one match(horizontal or diagonal or vertical).
     */
    private static boolean checkAll(int x, int y, char[][] dnaMatrix) {
        if (x > 5 || y > 5) {
            throw new IllegalArgumentException("Invalid dna length. Must be 6.");
        }
        Boolean[] response = new Boolean[3];
        response[0] = Boolean.TRUE;
        response[1] = Boolean.TRUE;
        response[2] = Boolean.TRUE;

        for (int i = 1; i <= 3 && !(response[0]==false && response[1]==false && response[2]==false); i++) {
            if (response[0]){
                response[0] = checkVertical(x,y,i,dnaMatrix);
            }
            if (response[1]){
                response[1] = checkDiagonal(x,y,i,dnaMatrix);
            }
            if (response[2]){
                response[2] = checkHorizontal(x,y,i,dnaMatrix);
            }
        }
        return response[0] || response[1] || response[2];
    }

    private static boolean checkVertical(int x, int y, int i,  char[][] dnaMatrix) throws RuntimeException {

        if (x + 3 < DNA_ROWS) {
            char dnaChar = dnaMatrix[x][y];
            return dnaMatrix[x + i][y] == dnaChar;
        }
        return false;
    }

    private static boolean checkDiagonal(int x, int y, int i,  char[][] dnaMatrix) {

        if (x + 3 < DNA_ROWS && y + 3 < DNA_COLUMNS) {
            char dnaChar = dnaMatrix[x][y];
            return dnaMatrix[x + i][y + i] == dnaChar;
        }
        return false;
    }

    private static boolean checkHorizontal(int x, int y, int i,  char[][] dnaMatrix) {

        if (y + 3 < DNA_COLUMNS) {
            char dnaChar = dnaMatrix[x][y];
            return dnaMatrix[x][y + i] == dnaChar;
        }
        return false;
    }
}
