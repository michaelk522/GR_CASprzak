package GR;

import functions.GeneralFunction;
import functions.commutative.Product;
import functions.commutative.Sum;
import functions.endpoint.Constant;
import functions.endpoint.Variable;
import functions.unitary.transforms.Differential;
import tools.DefaultFunctions;
import tools.exceptions.NotYetImplementedException;

import java.util.Arrays;

import static GR.LinearAlgebraTools.*;
import static tools.DefaultFunctions.*;


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
            Arrays.fill(row, ZERO);
        for (GeneralFunction[] row : inverseMetricMatrix)
            Arrays.fill(row, ZERO);

        for (int i = 0; i < x.length; i++) {
            metricMatrix[i][i] = x[i].simplify();
            inverseMetricMatrix[i][i] = reciprocal(x[i]).simplify();
        }

        metric = new Metric(this, metricMatrix, true);
        inverseMetric = new InverseMetric(this, inverseMetricMatrix, true);
    }

    public void defMetric(GeneralFunction[][] x) {
        if (!isSquare(x))
            throw new IllegalArgumentException("The matrix provided is not square: " + Arrays.deepToString(x));
        if(x.length == 0)
            throw new IllegalArgumentException("Can not have an metric of size 0.");
        if (!isSymmetric(x))
            throw new IllegalArgumentException("The matrix provided is not symmetric: " + Arrays.deepToString(x));

        metric = new Metric(this, x);

        if (isDiagonal(x))
            inverseMetric = new InverseMetric(this, inverseDiagonalMatrix(x), true);
        else
            inverseMetric = new InverseMetric(this, inverse(x), false);

    }

    public GeneralFunction[][][] christoffelConnection() {
        GeneralFunction[][][] christoffelConnection = new GeneralFunction[dim][dim][dim];
        for (int μ = 0; μ < dim; μ++) {
            for (int σ = 0; σ < dim; σ++) {
                for (int ν = 0; ν < dim; ν++) {

                    GeneralFunction[] sum = new GeneralFunction[dim];

                    for (int ρ = 0; ρ < dim; ρ++) {
                        sum[ρ] = new Product(
                                inverseMetric.matrix[σ][ρ],
                                new Sum(metric.matrix[ν][ρ].getSimplifiedDerivative(variableStrings[μ]),
                                        metric.matrix[ρ][μ].getSimplifiedDerivative(variableStrings[ν]),
                                        negative(metric.matrix[μ][ν].getSimplifiedDerivative(variableStrings[ρ]))));
                    }

                    christoffelConnection[μ][σ][ν] = new Product(HALF, new Sum(sum)).simplify();
                    System.out.println(variableStrings[μ] + " " + variableStrings[σ] + " " +variableStrings[ν] + " : " + christoffelConnection[μ][σ][ν].toString());
                }
            }
        }
        return christoffelConnection;
    }

    public GeneralFunction[][][][] riemannTensor() {
        GeneralFunction[][][][] riemannTensor = new GeneralFunction[dim][dim][dim][dim];
        GeneralFunction[][][] Γ = christoffelConnection();
        for (int λ = 0; λ < dim; λ++) {
            for (int ρ = 0; ρ < dim; ρ++) {
                for (int μ = 0; μ < dim; μ++) {
                    for (int ν = 0; ν < dim; ν++) {

                        GeneralFunction[] sum1 = new GeneralFunction[4];

                        sum1[0] = Γ[ν][λ][ρ].getSimplifiedDerivative(variableStrings[μ]);

                        sum1[1] = negative(Γ[μ][λ][ρ].getSimplifiedDerivative(variableStrings[ν]));

                        GeneralFunction[] sum2 = new GeneralFunction[dim];
                        for (int α = 0; α < dim; α++) {
                            sum2[α] = new Product(
                                    Γ[μ][λ][α],
                                    Γ[ν][α][ρ]
                            );
                        }
                        sum1[2] = new Sum(sum2).simplify();

                        GeneralFunction[] sum3 = new GeneralFunction[dim];
                        for (int α = 0; α < dim; α++) {
                            sum3[α] = new Product(
                                    Γ[ν][λ][α],
                                    Γ[μ][α][ρ]
                            );
                        }
                        sum1[3] = negative(new Sum(sum3)).simplify();

                        riemannTensor[λ][ρ][μ][ν] = new Sum(sum1).simplify();
                        System.out.println(variableStrings[λ] + " " + variableStrings[ρ] + " " + variableStrings[μ] + " " +variableStrings[ν] + " : " + riemannTensor[λ][ρ][μ][ν].toString());
                    }
                }
            }
        }

        return riemannTensor;
    }

    public GeneralFunction[][] ricciTensor() {
        GeneralFunction[][] ricciTensor = new GeneralFunction[dim][dim];
        GeneralFunction[][][][] R = riemannTensor();
        for (int ρ = 0; ρ < dim; ρ++) {
            for (int ν = 0; ν < dim; ν++) {

                GeneralFunction[] sum = new GeneralFunction[dim];

                for (int λ = 0; λ < dim; λ++) {
                    sum[λ] = R[λ][ρ][λ][ν];
                }

                ricciTensor[ρ][ν] = new Sum(sum).simplify();
                System.out.println(variableStrings[ρ] + " " +variableStrings[ν] + " : " + ricciTensor[ρ][ν].toString());

            }
        }

        return ricciTensor;
    }

    public GeneralFunction ricciScalar() {
        GeneralFunction[][] R = ricciTensor();

        GeneralFunction[] sum1 = new GeneralFunction[dim];
        for (int ν = 0; ν < dim; ν++) {
            GeneralFunction[] sum2 = new GeneralFunction[dim];
            for (int μ = 0; μ < dim; μ++) {
                sum2[μ] = new Product(inverseMetric.matrix[ν][μ], R[μ][ν]);
            }
            sum1[ν] = new Sum(sum2).simplify();
        }
         return new Sum(sum1).simplify();
    }

    public GeneralFunction ds() {
        GeneralFunction[] sum = new GeneralFunction[dim*dim];
        int counter = 0;
        for (int μ = 0; μ < dim; μ++) {
            for (int ν = 0; ν < dim; ν++) {

                sum[counter] = new Product(new Differential(variableStrings[μ]),
                        metric.matrix[μ][ν],
                        new Differential(variableStrings[ν]));

                counter++;
            }
        }
        return sqrt(new Sum(sum)).simplify();
    }

    public GeneralFunction[][] einsteinTensor() {
        GeneralFunction[][] G = ricciTensor();
        GeneralFunction R = ricciScalar();

        for (int μ = 0; μ < dim; μ++) {
            for (int ν = 0; ν < dim; ν++) {
                G[μ][ν] = new Sum(G[μ][ν], negative(new Product(new Constant(0.5), metric.matrix[μ][ν], R))).simplify();
                System.out.println(variableStrings[μ] + " " +variableStrings[ν] + " : " + G[μ][ν].toString());
            }
        }

        return G;
    }

   public GeneralFunction volumeElement() {
        return sqrt(determinant(metric.matrix)).simplify();
   }


}
