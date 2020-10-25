package GR;

import functions.GeneralFunction;
import functions.commutative.Product;
import functions.commutative.Sum;

import java.util.Arrays;

import static tools.DefaultFunctions.*;

public class LinearAlgebraTools {

    public static GeneralFunction[][] cofactorMatrix(GeneralFunction[][] input, int i, int j) {
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
                finalMatrix[k][l] = input[k < i ? k : k+1][l < j ? l : l+1];
            }
        }

        return finalMatrix;
    }

    public static GeneralFunction[][] cofactorMatrix(GeneralFunction[][] input, int j) {
        return cofactorMatrix(input, 0, j);
    }

    public static boolean isSquare(GeneralFunction[][] input) {
        int length = input.length;
        for (GeneralFunction[] array: input) {
            if (array.length != length)
                return false;
        }
        return true;
    }

    public static GeneralFunction determinant(GeneralFunction[][] input) {
        if (!isSquare(input))
            throw new IllegalArgumentException("The matrix provided is not square: " + Arrays.deepToString(input));
        if(input.length == 0)
            throw new IllegalArgumentException("Can not have an array of length 0.");

        if (input.length == 1)
            return input[0][0].simplify();
        if (input.length == 2)
            return new Sum(new Product(input[0][0], input[1][1]), negative(new Product(input[0][1], input[1][0]))).simplify();

        GeneralFunction[] terms = new GeneralFunction[input.length];

        for (int i = 0; i < terms.length; i++) {
            terms[i] = new Product(input[0][i], determinant(cofactorMatrix(input, i)), (i%2 == 0 ? ONE : NEGATIVE_ONE)).simplify();
        }

        return new Sum(terms).simplify();
    }

    public static GeneralFunction[][] inverse(GeneralFunction[][] input) {
        if (!isSquare(input))
            throw new IllegalArgumentException("The matrix provided is not square: " + Arrays.deepToString(input));
        if(input.length == 0)
            throw new IllegalArgumentException("Can not have an array of length 0.");

        if (input.length == 1)
            return new GeneralFunction[][]{
                    new GeneralFunction[]{reciprocal(input[0][0]).simplify()}
                    };

        GeneralFunction determinant = determinant(input);
        if (determinant.equals(ZERO))
            System.out.println("Determinant of the matrix provide was zero. Iffy stuff might have occurred.");


        GeneralFunction[][] cofactorMatrix = new GeneralFunction[input.length][input.length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                cofactorMatrix[i][j] = determinant(cofactorMatrix(input, i, j)).simplify();
            }
        }


        GeneralFunction[][] inverseMatrix = new GeneralFunction[input.length][input.length];

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                inverseMatrix[i][j] = new Product(reciprocal(determinant), cofactorMatrix[j][i]);
            }
        }

        return inverseMatrix;
    }

}
