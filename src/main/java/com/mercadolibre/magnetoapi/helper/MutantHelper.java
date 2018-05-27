package com.mercadolibre.magnetoapi.helper;

/**
 * Helper to determine if a human has the mutant gene.
 */
public class MutantHelper {

    /**
     * Check if a Dna has a mutant gene.
     * @param dnaSecuence Dna secuence.
     * @return has mutant gene.
     */
    public static boolean checkDna(String[] dnaSecuence) {
        char[][] dnaMatrix = createDnaMatrix(dnaSecuence);

        boolean res = false;
        for (int i = 0; i < 6 && !res; i++) {
            for (int j = 0; j < 6 && !res; j++) {
                res = checkAll(i,j,dnaMatrix);
            }
        }
        return res;
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
            throw new IllegalArgumentException("Invalid dna length. Must be 6.");
        }

        System.arraycopy(dnaArray, 0, dnaMatrix[index], 0, 6);
    }

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

        if (x + 3 < 6) {
            char dnaChar = dnaMatrix[x][y];
            return dnaMatrix[x + i][y] == dnaChar;
        }
        return false;
    }

    private static boolean checkDiagonal(int x, int y, int i,  char[][] dnaMatrix) {

        if (x + 3 < 6 && y + 3 < 6) {
            char dnaChar = dnaMatrix[x][y];
            return dnaMatrix[x + i][y + i] == dnaChar;
        }
        return false;
    }

    private static boolean checkHorizontal(int x, int y, int i,  char[][] dnaMatrix) {

        if (y + 3 < 6) {
            char dnaChar = dnaMatrix[x][y];
            return dnaMatrix[x][y + i] == dnaChar;
        }
        return false;
    }
}
