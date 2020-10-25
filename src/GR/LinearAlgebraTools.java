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




        return null;
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
