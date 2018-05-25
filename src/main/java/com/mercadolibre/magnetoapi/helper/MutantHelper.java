package com.mercadolibre.magnetoapi.helper;

/**
 * Helper to determine if a human has the mutant gene.
 */
public class MutantHelper {

    /**
     * Check if a Dna has a mutant gene.
     * @param dnaSecuence Dna secuence.
     * @return
     */
    public static boolean checkDna(String[] dnaSecuence) {
        char[][] dnaMatrix = createDnaMatrix(dnaSecuence);

        boolean res = false;
        for (int i = 0; i < 6 && !res; i++) {
            for (int j = 0; j < 6 && !res; j++) {
                res = checkVerticalMatch(i, j, dnaMatrix) ||
                        checkHorizontalMatch(i, j, dnaMatrix) ||
                        checkDiagonalMatch(i, j, dnaMatrix);
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

        for (int i = 0; i < 6; i++) {
            dnaMatrix[index][i] = dnaArray[i];
        }
    }

    private static boolean checkDiagonalMatch(int x, int y, char[][] dnaMatrix) throws RuntimeException {
        if (x > 5 || y > 5) {
            throw new IllegalArgumentException("Invalid dna length. Must be 6.");
        }

        if (x + 3 < 6 && y + 3 < 6) {
            char dnaChar = dnaMatrix[x][y];
            for (int i = 1; i <= 3; i++) {
                if (dnaMatrix[x + i][y + i] != dnaChar) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean checkHorizontalMatch(int x, int y, char[][] dnaMatrix) throws RuntimeException {
        if (x > 5 || y > 5) {
            throw new IllegalArgumentException("Invalid dna length. Must be 6.");
        }

        if (y + 3 < 6) {
            char dnaChar = dnaMatrix[x][y];
            for (int i = y + 1; i <= y + 3; i++) {
                if (dnaMatrix[x][i] != dnaChar) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean checkVerticalMatch(int x, int y, char[][] dnaMatrix) throws RuntimeException {
        if (x > 5 || y > 5) {
            throw new IllegalArgumentException("Invalid dna length. Must be 6.");
        }

        if (x + 3 < 6) {
            char dnaChar = dnaMatrix[x][y];
            for (int i = x + 1; i <= x + 3; i++) {
                if (dnaMatrix[i][y] != dnaChar) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
