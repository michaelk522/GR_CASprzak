package GR;

import GR.src.Space;
import functions.GeneralFunction;
import functions.commutative.Product;
import functions.commutative.Sum;
import functions.endpoint.Constant;
import functions.endpoint.Variable;
import functions.unitary.trig.normal.Sin;

import java.util.Arrays;

import static GR.DefaultSpaces.*;
import static tools.DefaultFunctions.*;

public class GR {
    public static void main(String[] args) {


        System.out.println();
//        System.out.println(Arrays.deepToString(DefaultSpaces.polar.metric.matrix));
//        System.out.println();
//        System.out.println(Arrays.deepToString(DefaultSpaces.cartesian3d.christoffelConnection()));
//        System.out.println();
//        System.out.println(Arrays.deepToString(DefaultSpaces.polar.riemannTensor()));
//        System.out.println();
//        System.out.println();
//        System.out.println(DefaultSpaces.s3.ricciScalar());

        Space torus = new Space("u", "v");
        torus.defMetric(square(new Sum(new Constant(1), new Product(new Constant(1), new Sin(new Variable("v"))))),
                square(new Constant(1)));

        System.out.println();
        System.out.println(Arrays.deepToString(torus.metric.matrix));
        System.out.println();
        System.out.println(Arrays.deepToString(torus.riemannTensor()));
        System.out.println();
        GeneralFunction a = torus.ricciScalar();
        System.out.println(a);
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 6; j++) {
//                System.out.println(a.evaluate(Map.of("u", (double)i, "v", (double)j)));
//            }
//        }


        System.out.println(spherical.ds());


        System.out.println(Arrays.deepToString(s3.einsteinTensor()));


        System.out.println(G);

        System.out.println(schwarzschild.ds());

        System.out.println(Arrays.deepToString(schwarzschild.ricciTensor()));

        System.out.println(subtract(ONE, new Product(TWO, reciprocal(r))).simplify());

        GeneralFunction f = schwarzschild.ricciScalar();



        Variable R_1 = new Variable("\\R_1");
        Variable R_2 = new Variable("\\R_2");
        Space torusIn4D = new Space("u", "v");
        torusIn4D.defMetric(
                square(R_1),
                square(R_2)
        );

        System.out.println(Arrays.deepToString(torusIn4D.ricciTensor()));


        System.out.println(Arrays.deepToString(polar.riemannTensor()));

        System.out.println(Arrays.deepToString(schwarzschild.ricciTensor()));

        System.out.println(Arrays.deepToString(s2.ricciTensor()));

        System.out.println(s2.ricciScalar());


    }


}

