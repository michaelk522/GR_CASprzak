package GR;
import functions.GeneralFunction;
import org.junit.jupiter.api.Test;
import parsing.FunctionParser;
import tools.singlevariable.Extrema;

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



}
