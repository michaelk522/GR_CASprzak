package GR;

import functions.GeneralFunction;
import functions.commutative.Sum;
import functions.unitary.transforms.Differential;
import org.junit.jupiter.api.Test;

import static GR.DefaultSpaces.*;
import static GR.LinearAlgebraTools.*;
import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void isPolarFlat() {
        GeneralFunction[][][][] test1 = polar.riemannTensor();
        GeneralFunction[][] test2 = polar.ricciTensor();
        GeneralFunction test3 = polar.ricciScalar();

        assertTrue(isZero(test1));
        assertTrue(isZero(test2));
        assertTrue(isZero(test3));
    }

    @Test
    void isSphericalFlat() {
        GeneralFunction[][][][] test1 = spherical.riemannTensor();
        GeneralFunction[][] test2 = spherical.ricciTensor();
        GeneralFunction test3 = spherical.ricciScalar();

        assertTrue(isZero(test1));
        assertTrue(isZero(test2));
        assertTrue(isZero(test3));
    }

    @Test
    void isCylindricalFlat() {
        GeneralFunction[][][][] test1 = cylindrical.riemannTensor();
        GeneralFunction[][] test2 = cylindrical.ricciTensor();
        GeneralFunction test3 = cylindrical.ricciScalar();

        assertTrue(isZero(test1));
        assertTrue(isZero(test2));
        assertTrue(isZero(test3));
    }

    @Test
    void isMinkowskiFlat() {
        GeneralFunction[][][][] test1 = minkowski.riemannTensor();
        GeneralFunction[][] test2 = minkowski.ricciTensor();
        GeneralFunction test3 = minkowski.ricciScalar();

        assertTrue(isZero(test1));
        assertTrue(isZero(test2));
        assertTrue(isZero(test3));
    }

    @Test
    void isS2NotFlat() {
        GeneralFunction[][][][] test1 = s2.riemannTensor();
        GeneralFunction[][] test2 = s2.ricciTensor();
        GeneralFunction test3 = s2.ricciScalar();

        assertFalse(isZero(test1));
        assertFalse(isZero(test2));
        assertFalse(isZero(test3));
    }

    @Test
    void isS3NotFlat() {
        GeneralFunction[][][][] test1 = s3.riemannTensor();
        GeneralFunction[][] test2 = s3.ricciTensor();
        GeneralFunction test3 = s3.ricciScalar();

        assertFalse(isZero(test1));
        assertFalse(isZero(test2));
        assertFalse(isZero(test3));
    }

    @Test
    void isSchwarzschildInSchwarzschildCoordinatesNotFlat() {
        GeneralFunction[][][][] test1 = schwarzschildInSchwarzschildCoordinates.riemannTensor();
        GeneralFunction[][] test2 = schwarzschildInSchwarzschildCoordinates.ricciTensor();
        GeneralFunction test3 = schwarzschildInSchwarzschildCoordinates.ricciScalar();

        assertFalse(isZero(test1));
        assertFalse(isZero(test2));
        assertFalse(isZero(test3));
    }

}
