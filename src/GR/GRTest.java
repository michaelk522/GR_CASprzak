package GR;
import functions.GeneralFunction;
import org.junit.jupiter.api.Test;
import parsing.FunctionParser;
import tools.singlevariable.Extrema;

import java.util.Arrays;

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

        assertArrayEquals(test2, subMatrix(test1, 0, 1));
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

        assertArrayEquals(test2, subMatrix(test1, 3, 2));
    }



}
