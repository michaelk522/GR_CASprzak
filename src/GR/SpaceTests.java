package GR;

import functions.GeneralFunction;
import functions.commutative.Sum;
import functions.unitary.transforms.Differential;
import org.junit.jupiter.api.Test;

import static GR.DefaultSpaces.*;
import static GR.LinearAlgebraTools.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tools.DefaultFunctions.*;

public class SpaceTests {

    @Test
    void dsOfCartesianSpace() {
        GeneralFunction test1 = sqrt(new Sum(square(new Differential("x")), square(new Differential("y"))));
        assertEquals(test1, cartesian2d.ds());
    }

    @Test
    void isCartesianFlat() {
        GeneralFunction[][][][] test1 = cartesian3d.riemannTensor();
        GeneralFunction[][] test2 = cartesian3d.ricciTensor();
        GeneralFunction test3 = cartesian3d.ricciScalar();

        assertTrue(isZero(test1));
        assertTrue(isZero(test2));
        assertTrue(isZero(test3));
    }

}
