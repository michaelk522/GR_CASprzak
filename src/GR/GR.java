package GR;

import functions.GeneralFunction;
import functions.binary.Pow;
import functions.commutative.Product;
import functions.commutative.Sum;
import functions.endpoint.Constant;
import functions.endpoint.Variable;
import functions.unitary.trig.normal.Sin;
import tools.DefaultFunctions;

import java.util.Arrays;

@SuppressWarnings("NonAsciiCharacters")
public class GR {
    public static void main(String[] args) {
        Variable r = new Variable("r");
        Variable θ = new Variable("θ");
        Variable φ = new Variable("φ");


        String[] variables = {"r",
                              "θ",
                              "φ"};
        GeneralFunction[][] metricTensor = new GeneralFunction[3][3];
        GeneralFunction[][] inverseMetricTensor = new GeneralFunction[3][3];
        GeneralFunction[][][] christoffelConnection = new GeneralFunction[3][3][3];

        for (GeneralFunction[] row : metricTensor)
            Arrays.fill(row, new Constant(0));
        for (GeneralFunction[] row : inverseMetricTensor)
            Arrays.fill(row, new Constant(0));

        metricTensor[0][0] = new Constant(1);
        metricTensor[1][1] = new Pow(new Constant(2), r);
        metricTensor[2][2] = new Product(new Pow(new Constant(2), r), new Pow(new Constant(2), new Sin(θ)));

        inverseMetricTensor[0][0] = new Constant(1);
        inverseMetricTensor[1][1] = new Pow(new Constant(-2), r);
        inverseMetricTensor[2][2] = new Product(new Pow(new Constant(-2), r), new Pow(new Constant(-2), new Sin(θ)));

        System.out.println(Arrays.toString(metricTensor));

        for (int μ = 0; μ < 3; μ++) {
            for (int σ = 0; σ < 3; σ++) {
                for (int ν = 0; ν < 3; ν++) {
                    GeneralFunction zeroth;
                    GeneralFunction first;
                    GeneralFunction second;

                    zeroth = new Product(inverseMetricTensor[σ][0], new Sum(metricTensor[ν][0].getSimplifiedDerivative(variables[μ]),
                                                                            metricTensor[0][μ].getSimplifiedDerivative(variables[ν]),
                                                  DefaultFunctions.negative(metricTensor[μ][ν].getSimplifiedDerivative(variables[0]))));

                    first = new Product(inverseMetricTensor[σ][1], new Sum(metricTensor[ν][1].getSimplifiedDerivative(variables[μ]),
                                                                           metricTensor[1][μ].getSimplifiedDerivative(variables[ν]),
                                                 DefaultFunctions.negative(metricTensor[μ][ν].getSimplifiedDerivative(variables[1]))));

                    second = new Product(inverseMetricTensor[σ][2], new Sum(metricTensor[ν][2].getSimplifiedDerivative(variables[μ]),
                                                                            metricTensor[2][μ].getSimplifiedDerivative(variables[ν]),
                                                  DefaultFunctions.negative(metricTensor[μ][ν].getSimplifiedDerivative(variables[2]))));

                    christoffelConnection[μ][σ][ν] = new Product(new Constant(0.5), new Sum(zeroth, first, second)).simplify();
                    System.out.println(variables[μ] + " " + variables[σ] + " " + variables[ν] + " : " + christoffelConnection[μ][σ][ν].toString());
                }
            }
        }

        System.out.println();
        System.out.println(Arrays.deepToString(metricTensor));
        System.out.println();
        System.out.println(Arrays.deepToString(christoffelConnection));



    }
}

