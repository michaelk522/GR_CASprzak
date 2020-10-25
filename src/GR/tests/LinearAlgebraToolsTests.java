package GR.tests;
import functions.GeneralFunction;
import functions.binary.Pow;
import functions.commutative.Product;
import functions.commutative.Sum;
import functions.endpoint.Constant;
import org.junit.jupiter.api.Test;
import parsing.FunctionParser;

import java.util.Map;

import static GR.src.LinearAlgebraTools.*;
import static org.junit.jupiter.api.Assertions.*;
import static tools.DefaultFunctions.*;


public class LinearAlgebraToolsTests {

    @Test
    void isSquareTrue() {
        GeneralFunction[][] test = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, TWO},
                new GeneralFunction[]{HALF, TEN},
        };

        assertTrue(isSquare(test));
    }

    @Test
    void isSquareFalse() {
        GeneralFunction[][] test = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, TWO},
                new GeneralFunction[]{HALF},
        };

        assertFalse(isSquare(test));
    }


    @Test
    void simpleSubMatrix() {
        GeneralFunction[][] test1 = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, TWO},
                new GeneralFunction[]{HALF, ONE},
        };

        GeneralFunction[][] test2 = new GeneralFunction[][]{
                new GeneralFunction[]{HALF}
        };

        assertArrayEquals(test2, cofactorMatrix(test1, 0, 1));
    }


    @Test
    void notSoSimpleSubMatrix() {
        GeneralFunction[][] test1 = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE, ONE},
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE, ONE},
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE, ONE},
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE, ONE},
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE, ONE},
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE, ONE}
        };

        GeneralFunction[][] test2 = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE},
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE},
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE},
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE},
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE},
        };

        assertArrayEquals(test2, cofactorMatrix(test1, 3, 2));
    }

    @Test
    void simpleDeterminant1() {
        GeneralFunction[][] test1 = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, TWO},
                new GeneralFunction[]{HALF, ONE},
        };

        assertEquals(ZERO, determinant(test1));
    }

    @Test
    void simpleDeterminant2() {
        GeneralFunction[][] test1 = new GeneralFunction[][]{
                new GeneralFunction[]{ONE}
        };

        assertEquals(ONE, determinant(test1));
    }

    @Test
    void simpleButLongDeterminant() {
        GeneralFunction[][] test1 = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE},
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE},
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE},
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE},
                new GeneralFunction[]{ONE, ONE, ONE, ONE, ONE},
        };

        assertEquals(ZERO, determinant(test1));
    }

    @Test
    void ComplexDeterminant() {
        GeneralFunction[][] test1 = new GeneralFunction[][]{
                new GeneralFunction[]{new Pow(X, E), X, TWO},
                new GeneralFunction[]{X, new Pow(X, E), X},
                new GeneralFunction[]{ONE, X, NEGATIVE_ONE}
        };

        GeneralFunction test2 = FunctionParser.parseSimplified("-(e^(2x))-(e^x)*(x^2)+4x^2-2e^x");

        assertEquals(test2, determinant(test1));
    }

    @Test
    void simpleInverse() {
        GeneralFunction[][] test1 = new GeneralFunction[][]{
                new GeneralFunction[]{ONE}
        };

        assertArrayEquals(test1, inverse(test1));
    }

    @Test
    void simpleInverse2x2() {
        GeneralFunction[][] test1 = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, TWO},
                new GeneralFunction[]{HALF, HALF},
        };

        GeneralFunction[][] test2 = new GeneralFunction[][]{
                new GeneralFunction[]{NEGATIVE_ONE, new Constant(4)},
                new GeneralFunction[]{ONE, new Constant(-2)},
        };

        assertArrayEquals(test2, inverse(test1));
    }

    @Test
    void simpleInverse3x3() {
        GeneralFunction[][] test1 = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, ZERO, ZERO},
                new GeneralFunction[]{ZERO, X, ZERO},
                new GeneralFunction[]{ZERO, ZERO, new Pow(TWO, X)}
        };

        GeneralFunction[][] test2 = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, ZERO, ZERO},
                new GeneralFunction[]{ZERO, new Pow(NEGATIVE_ONE, X), ZERO},
                new GeneralFunction[]{ZERO, ZERO, new Pow(NEGATIVE_TWO, X)}
        };

        assertArrayEquals(test2, inverse(test1));
    }

    @Test
    void inverseSymmetric4x4() {
        GeneralFunction[][] test1 = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, ZERO, ZERO, ZERO},
                new GeneralFunction[]{ZERO, X, ZERO, ZERO},
                new GeneralFunction[]{ZERO, ZERO, X, new Sum(X, ONE)},
                new GeneralFunction[]{ZERO, ZERO, new Sum(X, ONE), X}
        };

        GeneralFunction[][] test2 = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, ZERO, ZERO, ZERO},
                new GeneralFunction[]{ZERO, new Pow(NEGATIVE_ONE, X), ZERO, ZERO},
                new GeneralFunction[]{ZERO, ZERO, negative(frac(X, new Sum(new Product(TWO, X), ONE))).simplify(), frac(new Sum(X, ONE), new Sum(new Product(TWO, X), ONE)).simplify()},
                new GeneralFunction[]{ZERO, ZERO, frac(new Sum(X, ONE), new Sum(new Product(TWO, X), ONE)).simplify(), negative(frac(X, new Sum(new Product(TWO, X), ONE))).simplify()}
        };


        assertEquals(test2[3][3].evaluate(Map.of("x", 10.0)), inverse(test1)[3][3].evaluate(Map.of("x", 10.0)), 0.001);
    }

    @Test
    void isDiagonalTrue() {
        GeneralFunction[][] test = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, ZERO},
                new GeneralFunction[]{ZERO, TEN}
        };

        assertTrue(isDiagonal(test));
    }

    @Test
    void isDiagonalFalse() {
        GeneralFunction[][] test = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, ZERO},
                new GeneralFunction[]{TWO, TEN}
        };

        assertFalse(isDiagonal(test));
    }

    @Test
    void isDiagonal1x1() {
        GeneralFunction[][] test = new GeneralFunction[][]{
                new GeneralFunction[]{ONE}
        };

        assertTrue(isDiagonal(test));
    }

    @Test
    void isSymmetricTrue() {
        GeneralFunction[][] test = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, TWO},
                new GeneralFunction[]{TWO, TEN}
        };

        assertTrue(isSymmetric(test));
    }

    @Test
    void isSymmetricFalse() {
        GeneralFunction[][] test = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, ZERO},
                new GeneralFunction[]{TWO, TEN}
        };

        assertFalse(isSymmetric(test));
    }

    @Test
    void inverseOfADiagonalMatrix() {
        GeneralFunction[][] test1 = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, ZERO, ZERO, ZERO},
                new GeneralFunction[]{ZERO, X, ZERO, ZERO},
                new GeneralFunction[]{ZERO, ZERO, X, ZERO},
                new GeneralFunction[]{ZERO, ZERO, ZERO, X}
        };

        GeneralFunction[][] test2 = new GeneralFunction[][]{
                new GeneralFunction[]{ONE, ZERO, ZERO, ZERO},
                new GeneralFunction[]{ZERO, reciprocal(X), ZERO, ZERO},
                new GeneralFunction[]{ZERO, ZERO, reciprocal(X), ZERO},
                new GeneralFunction[]{ZERO, ZERO, ZERO, reciprocal(X)}
        };


        assertArrayEquals(test2, inverseDiagonalMatrix(test1));
    }


}
