package GR.src;

import functions.GeneralFunction;
import functions.commutative.Product;
import functions.commutative.Sum;

import java.util.Arrays;

import static tools.DefaultFunctions.*;

/**
 * This class contains many methods that perform various linear algebra tasks.
 */
public class LinearAlgebraTools {

    /**
     * Calculates the cofactor of a matrix {@code input} that excludes row {@code i} and column {@code j}
     * @param input the matrix whose cofactor is being found
     * @param i the row that is being excluded
     * @param j the column that is being excluded
     * @return the cofactor matrix
     */
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

    /**
     * Calculates the cofactor of a matrix {@code input} that excludes row {@code 0} and column {@code j}
     * @param input the matrix whose cofactor is being found
     * @param j the column that is being excluded
     * @return the cofactor matrix
     */
    public static GeneralFunction[][] cofactorMatrix(GeneralFunction[][] input, int j) {
        return cofactorMatrix(input, 0, j);
    }

    /**
     * Calculates the determinant of a matrix {@code input}
     * @param input the matrix whose determinant is being found
     * @return the determinant
     */
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

    /**
     * Calculates the inverse matrix of a matrix {@code input}
     * @param input the matrix whose inverse is being found
     * @return the inverse matrix
     */
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
                cofactorMatrix[i][j] = determinant(cofactorMatrix(input, i, j)).simplify();//The sign is added in the next for-loop
            }
        }


        GeneralFunction[][] inverseMatrix = new GeneralFunction[input.length][input.length];

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                inverseMatrix[i][j] = new Product(reciprocal(determinant).simplify(), cofactorMatrix[j][i], ((i+j)%2 ==0 ? ONE : NEGATIVE_ONE)).simplify();
            }
        }

        return inverseMatrix;
    }

    /**
     * Checks if the matrix is diagonal
     * @param input matrix that is being checked
     * @return true if the matrix is diagonal
     */
    public static boolean isDiagonal(GeneralFunction[][] input) {
        if (!isSquare(input))
            throw new IllegalArgumentException("The matrix provided is not square: " + Arrays.deepToString(input));
        if(input.length == 0)
            throw new IllegalArgumentException("Can not have an array of length 0.");


        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (i != j && !input[i][j].equals(ZERO))
                    return false;
            }
        }
        return true;
    }

    /**
     * Checks if the matrix is symmetric
     * @param input matrix that is being checked
     * @return true if the matrix is symmetric
     */
    public static boolean isSymmetric(GeneralFunction[][] input) {
        if (!isSquare(input))
            throw new IllegalArgumentException("The matrix provided is not square: " + Arrays.deepToString(input));
        if(input.length == 0)
            throw new IllegalArgumentException("Can not have an array of length 0.");

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < i; j++) {
                if (!input[i][j].equals(input[j][i]))
                    return false;
            }
        }
        return true;
    }

    /**
     * Checks if the matrix is square
     * @param input matrix that is being checked
     * @return true if the matrix is square
     */
    public static boolean isSquare(GeneralFunction[][] input) {
        int length = input.length;
        for (GeneralFunction[] array: input) {
            if (array.length != length)
                return false;
        }
        return true;
    }

    /**
     * Calculates of inverse matrix of a diagonal matrix
     * DOES NOT CHECK IF MATRIX IS DIAGONAL
     * @param input matrix whose inverse is being found
     * @return the inverse matrix
     */
    public static GeneralFunction[][] inverseDiagonalMatrix(GeneralFunction[][] input) {
        GeneralFunction[][] inverse = Arrays.stream(input)
                .map(GeneralFunction[]::clone)
                .toArray(GeneralFunction[][]::new);

        for (int i = 0; i < input.length; i++) {
            inverse[i][i] = reciprocal(input[i][i]).simplify();
        }
        
        return inverse;
    }

    /**
     * Calculates of determinant of a diagonal matrix
     * DOES NOT CHECK IF MATRIX IS DIAGONAL
     * @param input matrix whose determinant is being found
     * @return the determinant
     */
    public static GeneralFunction determinantDiagonalMatrix(GeneralFunction[][] input) {
        GeneralFunction[] terms = new GeneralFunction[input.length];

        for (int i = 0; i < input.length; i++) {
            terms[i] = input[i][i];
        }

        return new Product(terms).simplify();
    }

    /**
     * Checks if the function is zero
     * @param input function that is being checked
     * @return true if the function is zero
     */
    public static boolean isZero(GeneralFunction input) {
        return input.equals(ZERO);
    }

    /**
     * Checks if the matrix is zero
     * @param input matrix that is being checked
     * @return true if the matrix is zero
     */
    public static boolean isZero(GeneralFunction[][] input) {
        for (GeneralFunction[] array : input) {
            for (GeneralFunction function : array) {
                if (!function.equals(ZERO))
                    return false;
            }
        }

        return true;
    }

    /**
     * Checks if the rank 3 tensor is zero
     * @param input rank 3 tensor that is being checked
     * @return true if the rank 3 tensor is zero
     */
    public static boolean isZero(GeneralFunction[][][] input) {
        for (GeneralFunction[][] array1 : input) {
            for (GeneralFunction[] array2 : array1) {
                for (GeneralFunction function : array2) {
                    if (!function.equals(ZERO))
                        return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if the rank 4 tensor is zero
     * @param input rank 4 tensor that is being checked
     * @return true if the rank 4 tensor is zero
     */
    public static boolean isZero(GeneralFunction[][][][] input) {
        for (GeneralFunction[][][] array1 : input) {
            for (GeneralFunction[][] array2 : array1) {
                for (GeneralFunction[] array3 : array2) {
                    for (GeneralFunction function : array3) {
                        if (!function.equals(ZERO))
                            return false;
                    }
                }
            }
        }

        return true;
    }

}
