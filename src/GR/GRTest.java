package GR;
import functions.GeneralFunction;
import functions.binary.Pow;
import org.junit.jupiter.api.Test;
import parsing.FunctionParser;

import static GR.LinearAlgebraTools.*;
import static org.junit.jupiter.api.Assertions.*;
import static tools.DefaultFunctions.*;


public class GRTest {

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


}
