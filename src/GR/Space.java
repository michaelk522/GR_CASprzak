package GR;

import functions.GeneralFunction;
import functions.commutative.Product;
import functions.commutative.Sum;
import functions.endpoint.Constant;
import functions.endpoint.Variable;
import tools.DefaultFunctions;
import tools.exceptions.NotYetImplementedException;

import java.util.Arrays;


@SuppressWarnings("NonAsciiCharacters")
public class Space {
    public int dim;
    public String[] variableStrings;
    public Variable[] variables;

    public Metric metric;
    public InverseMetric inverseMetric;

    public Space(String... variableNames) {
        dim = variableNames.length;
        variableStrings = variableNames;
        variables = Arrays.stream(variableNames).map(Variable::new).toArray(Variable[]::new);
    }

    public Space(String[] variableNames, GeneralFunction[] x) {
        dim = variableNames.length;
        variableStrings = variableNames;
        variables = Arrays.stream(variableNames).map(Variable::new).toArray(Variable[]::new);
        defMetric(x);
    }

    public Space(String[] variableNames, GeneralFunction[][] x) {
        dim = variableNames.length;
        variableStrings = variableNames;
        variables = Arrays.stream(variableNames).map(Variable::new).toArray(Variable[]::new);
        defMetric(x);
    }

    public void defMetric(GeneralFunction... x) {
        if (x.length != dim)
            throw new IllegalArgumentException("Expected " + dim + " functions but instead got " + x.length + ".");

        GeneralFunction[][] metricMatrix = new GeneralFunction[dim][dim];
        GeneralFunction[][] inverseMetricMatrix = new GeneralFunction[dim][dim];

        for (GeneralFunction[] row : metricMatrix)
            Arrays.fill(row, new Constant(0));
        for (GeneralFunction[] row : inverseMetricMatrix)
            Arrays.fill(row, new Constant(0));

        for (int i = 0; i < x.length; i++) {
            metricMatrix[i][i] = x[i].simplify();
            inverseMetricMatrix[i][i] = DefaultFunctions.reciprocal(x[i]).simplify();
        }

        metric = new Metric(this, metricMatrix);
        inverseMetric = new InverseMetric(this, inverseMetricMatrix);
    }

    public void defMetric(GeneralFunction[][] x) {
        throw new NotYetImplementedException("We dont invert matrices yet.");
//        if (x.length != dim || x[0].length != dim)
//            throw new IllegalArgumentException("Expected " + dim + " dimensions but instead got " + x.length + ".");
//
//        GeneralFunction[][] metricMatrix = x;
//        GeneralFunction[][] inverseMetricMatrix = x;
//
//        for (int i = 0; i < x.length; i++) {
//            metricMatrix[i][i] = x[i].simplify();
//            inverseMetricMatrix[i][i] = DefaultFunctions.reciprocal(x[i]).simplify();
//        }
//
//        metric = new Metric(this, metricMatrix);
//        inverseMetric = new InverseMetric(this, inverseMetricMatrix);
    }

    public GeneralFunction[][][] christoffelConnection() {
        GeneralFunction[][][] christoffelConnection = new GeneralFunction[dim][dim][dim];
        for (int μ = 0; μ < dim; μ++) {
            for (int σ = 0; σ < dim; σ++) {
                for (int ν = 0; ν < dim; ν++) {

                    GeneralFunction[] sum = new GeneralFunction[dim];

                    for (int ρ = 0; ρ < dim; ρ++) {
                        sum[ρ] = new Product(inverseMetric.matrix[σ][ρ], new Sum(metric.matrix[ν][ρ].getSimplifiedDerivative(variableStrings[μ]),
                                metric.matrix[ρ][μ].getSimplifiedDerivative(variableStrings[ν]),
                                DefaultFunctions.negative(metric.matrix[μ][ν].getSimplifiedDerivative(variableStrings[ρ]))));
                    }

                    christoffelConnection[μ][σ][ν] = new Product(new Constant(0.5), new Sum(sum)).simplify();
                    System.out.println(variableStrings[μ] + " " + variableStrings[σ] + " " +variableStrings[ν] + " : " + christoffelConnection[μ][σ][ν].toString());
                }
            }
        }
        return christoffelConnection;
    }


}
