package GR;

import functions.binary.Pow;
import functions.commutative.Product;
import functions.endpoint.Constant;
import functions.endpoint.Variable;
import functions.unitary.trig.normal.Sin;

import java.util.Arrays;

public class GR {
    public static void main(String[] args) {

        Space sphericalCoordinates = new Space("r", "θ", "φ");
        sphericalCoordinates.defMetric(new Constant(1),
                                       new Pow(new Constant(2), new Variable("r")),
                                       new Pow(new Constant(2), new Product(new Variable("r"), new Sin(new Variable("θ")))));

        System.out.println();
        System.out.println(Arrays.deepToString(sphericalCoordinates.metric.matrix));
        System.out.println();
        System.out.println(Arrays.deepToString(sphericalCoordinates.christoffelConnection()));



    }


}

