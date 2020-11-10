package GR;

import GR.src.LinearAlgebraTools;
import GR.src.Space;
import functions.commutative.Sum;
import functions.endpoint.Variable;
import functions.unitary.trig.normal.Sin;

import java.util.Arrays;

import static tools.DefaultFunctions.ONE;
import static tools.DefaultFunctions.square;

public class TorusExample {

    // This code will go through the simple problem of proving that a 2D torus that lives in 3D is not flat
    // but living in 4D, it is riemann flat
    public static void main(String[] args) {

        // Here we are defining the torus that lives in 3D
        Space torus3D = new Space("u", "v");
        torus3D.defMetric(
                square(new Sum(ONE, new Sin(new Variable("v")))),
                ONE
        );

        System.out.println();
        System.out.println(Arrays.deepToString(torus3D.metric.matrix));
        System.out.println();
        System.out.println(Arrays.deepToString(torus3D.riemannTensor()));
        System.out.println();
        System.out.println(torus3D.ricciScalar());



        // As you can see if you ran the code, we got a horrible trig mess, aka its not flat

        // Just to be sure we can call the isZero method
        System.out.println();
        System.out.println(LinearAlgebraTools.isZero(torus3D.riemannTensor()));
        System.out.println();

        // Now time for 4D

        // Here we define the variable that we will be using
        Variable R_1 = new Variable("\\R_1");
        Variable R_2 = new Variable("\\R_2");

        // This defines the Space that we will be working in
        Space torus4D = new Space("u", "v");
        torus4D.defMetric(
                square(R_1),
                square(R_2)
        );


        System.out.println(Arrays.deepToString(torus4D.ricciTensor()));

        // That looked like all 0s but just to be sure we can call the isZero method
        System.out.println();
        System.out.println(LinearAlgebraTools.isZero(torus4D.riemannTensor()));
        System.out.println();

    }

}
