package GR;

import functions.GeneralFunction;
import functions.binary.Pow;
import functions.commutative.Product;
import functions.endpoint.Constant;
import functions.endpoint.Variable;
import functions.unitary.trig.normal.Sin;

public class ChristoffelConnection {
    public static void main(String[] args) {
        Variable r = new Variable("r");
        Variable θ = new Variable("θ");
        Variable φ = new Variable("φ");

        GeneralFunction[][] metricTensor = new GeneralFunction[3][3];
        GeneralFunction[][] inverseMetricTensor = new GeneralFunction[3][3];
        GeneralFunction[][][] christoffelConnection = new GeneralFunction[3][3][3];

        metricTensor[0][0] = new Constant(1);
        metricTensor[0][1] = new Constant(0);
        metricTensor[0][2] = new Constant(0);
        metricTensor[1][0] = new Constant(0);
        metricTensor[1][1] = new Pow(new Constant(2), r);
        metricTensor[1][2] = new Constant(0);
        metricTensor[2][0] = new Constant(0);
        metricTensor[2][1] = new Constant(0);
        metricTensor[2][2] = new Product(new Pow(new Constant(2), r), new Pow(new Constant(2), new Sin(θ)));

        inverseMetricTensor[0][0] = new Constant(1);
        inverseMetricTensor[0][1] = new Constant(0);
        inverseMetricTensor[0][2] = new Constant(0);
        inverseMetricTensor[1][0] = new Constant(0);
        inverseMetricTensor[1][1] = new Pow(new Constant(-2), r);
        inverseMetricTensor[1][2] = new Constant(0);
        inverseMetricTensor[2][0] = new Constant(0);
        inverseMetricTensor[2][1] = new Constant(0);
        inverseMetricTensor[2][2] = new Product(new Pow(new Constant(-2), r), new Pow(new Constant(-2), new Sin(θ)));



    }
}
