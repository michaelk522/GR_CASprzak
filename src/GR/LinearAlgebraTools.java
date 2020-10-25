package GR;

import functions.GeneralFunction;

import java.util.Arrays;

public class LinearAlgebraTools {

    public static GeneralFunction[][] subMatrix(GeneralFunction[][] input, int i, int j) {
        if (!isSquare(input))
            throw new IllegalArgumentException("The matrix provided is not square: " + Arrays.deepToString(input));
        if (i < 0 || i >= input.length)
            throw new IllegalArgumentException("The row i:" + i + "that you want to exclude needs to be in the array.");
        if (j < 0 || j >= input.length)
            throw new IllegalArgumentException("The column j:" + j + "that you want to exclude needs to be in the array.");
        if (input.length == 1)
            throw new IllegalArgumentException("The sub-matrix of a 1x1 matrix will be null.");

        GeneralFunction[][] finalMatrix = new GeneralFunction[input.length-1][input.length-1];


        for (int k = 0; k < finalMatrix.length; k++) {
            for (int l = 0; l < finalMatrix.length; l++) {
                if (k < i && l < j)
                    finalMatrix[k][l] = input[k][l];
                else if (k < i && l >= j)
                    finalMatrix[k][l] = input[k][l+1];
                else if (k >= i && l < j)
                    finalMatrix[k][l] = input[k+1][l];
                else
                    finalMatrix[k][l] = input[k+1][l+1];
            }
        }

        return finalMatrix;
    }

    public static boolean isSquare(GeneralFunction[][] input) {
        int length = input.length;
        for (GeneralFunction[] array: input) {
            if (array.length != length)
                return false;
        }
        return true;
    }

}
