package GR;

import functions.binary.Pow;
import functions.commutative.Product;
import functions.endpoint.Constant;
import functions.endpoint.Variable;
import functions.unitary.trig.normal.Sin;

import java.util.Arrays;

public class GR {
    public static void main(String[] args) {


        System.out.println();
        System.out.println(Arrays.deepToString(DefaultSpaces.s3.metric.matrix));
        System.out.println();
        System.out.println(Arrays.deepToString(DefaultSpaces.s3.christoffelConnection()));



    }


}

