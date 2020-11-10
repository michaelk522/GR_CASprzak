package GR.src;

import functions.GeneralFunction;
import functions.commutative.Product;
import functions.commutative.Sum;
import functions.endpoint.Constant;
import functions.endpoint.Variable;
import functions.unitary.transforms.Differential;

import java.util.Arrays;

import static GR.src.LinearAlgebraTools.*;
import static tools.DefaultFunctions.*;

/**
 * A {@link Space} is the object that pertains to a Space or Geometry.
 */
@SuppressWarnings("NonAsciiCharacters")
public class Space {
    /**
     * The dimension of the {@link Space}
     */
    public int dim;

    /**
     * The strings of the {@link Variable}s of the {@link Space}
     */
    public String[] variableStrings;

    /**
     * The {@link Variable}s of the {@link Space}
     */
    public Variable[] variables;

    /**
     * The {@link Metric} of the {@link Space}
     */
    public Metric metric;
    /**
     * The {@link InverseMetric} of the {@link Space}
     */
    public InverseMetric inverseMetric;

    private GeneralFunction[][][] christoffelConnection = null;
    private GeneralFunction[][][][] riemannTensor = null;
    private GeneralFunction[][] ricciTensor = null;
    private GeneralFunction ricciScalar = null;
    private GeneralFunction[][] einsteinTensor = null;

    /**
     * Constructs a new {@link Space}
     * @param variableNames the names of the {@link Variable}s of the {@code Space}
     */
    public Space(String... variableNames) {
        dim = variableNames.length;
        variableStrings = variableNames;
        variables = Arrays.stream(variableNames).map(Variable::new).toArray(Variable[]::new);
    }

    /**
     * Constructs a new {@link Space}
     * @param variableNames the names of the {@link Variable}s of the {@code Space}
     * @param metric the diagonal {@code Metric} of the code as a {@code GeneralFunction[]}
     */
    public Space(String[] variableNames, GeneralFunction[] metric) {
        dim = variableNames.length;
        variableStrings = variableNames;
        variables = Arrays.stream(variableNames).map(Variable::new).toArray(Variable[]::new);
        defMetric(metric);
    }

    /**
     * Constructs a new {@link Space}
     * @param variableNames the names of the {@link Variable}s of the {@code Space}
     * @param metric the {@code Metric} of the code as a {@code GeneralFunction[][]}
     */
    public Space(String[] variableNames, GeneralFunction[][] metric) {
        dim = variableNames.length;
        variableStrings = variableNames;
        variables = Arrays.stream(variableNames).map(Variable::new).toArray(Variable[]::new);
        defMetric(metric);
    }

    /**
     * Defines the {@link Metric}  for the the {@link Space}
     * @param metric the diagonal {@code Metric} that is being passed in as a {@code GeneralFunction[]}
     */
    public void defMetric(GeneralFunction... metric) {
        if (metric.length != dim)
            throw new IllegalArgumentException("Expected " + dim + " functions but instead got " + metric.length + ".");

        GeneralFunction[][] metricMatrix = new GeneralFunction[dim][dim];
        GeneralFunction[][] inverseMetricMatrix = new GeneralFunction[dim][dim];

        for (GeneralFunction[] row : metricMatrix)
            Arrays.fill(row, ZERO);
        for (GeneralFunction[] row : inverseMetricMatrix)
            Arrays.fill(row, ZERO);

        for (int i = 0; i < metric.length; i++) {
            metricMatrix[i][i] = metric[i].simplify();
            inverseMetricMatrix[i][i] = reciprocal(metric[i]).simplify();
        }

        this.metric = new Metric(metricMatrix, true);
        inverseMetric = new InverseMetric(inverseMetricMatrix, true);
    }

    /**
     * Defines the {@link Metric}  for the the {@link Space}
     * @param metric the {@code Metric} that is being passed in as a {@code GeneralFunction[][]}
     */
    public void defMetric(GeneralFunction[][] metric) {
        if (!isSquare(metric))
            throw new IllegalArgumentException("The matrix provided is not square: " + Arrays.deepToString(metric));
        if(metric.length == 0)
            throw new IllegalArgumentException("Can not have an metric of size 0.");
        if (!isSymmetric(metric))
            throw new IllegalArgumentException("The matrix provided is not symmetric: " + Arrays.deepToString(metric));

        for (int i = 0; i < metric.length; i++) {
            for (int j = 0; j < metric.length; j++) {
                metric[i][j] = metric[i][j].simplify();
            }
        }

        if (isDiagonal(metric)) {
            this.metric = new Metric(metric, true);
            inverseMetric = new InverseMetric(inverseDiagonalMatrix(metric), true);
        } else {
            this.metric = new Metric(metric, false);
            inverseMetric = new InverseMetric(inverse(metric), false);
        }

    }

    /**
     * Gets the Christoffel Connection of the the {@link Space}
     * @return the Christoffel Connection
     */
    public GeneralFunction[][][] christoffelConnection() {
        if (christoffelConnection == null)
            calculateChristoffelConnection();
        return christoffelConnection;
    }

    /**
     * Gets the Riemann Tensor of the the {@link Space}
     * @return the Riemann Tensor
     */
    public GeneralFunction[][][][] riemannTensor() {
        if (riemannTensor == null)
            calculateRiemannTensor();
        return riemannTensor;
    }

    /**
     * Gets the Ricci Tensor of the the {@link Space}
     * @return the Ricci Tensor
     */
    public GeneralFunction[][] ricciTensor() {
        if (ricciTensor == null)
            calculateRicciTensor();
        return ricciTensor;
    }

    /**
     * Gets the Ricci Scalar of the the {@link Space}
     * @return the Ricci Scalar
     */
    public GeneralFunction ricciScalar() {
        if (ricciScalar == null)
            calculateRicciScalar();
        return ricciScalar;
    }

    /**
     * Gets the Einstein Tensor of the the {@link Space}
     * @return the Einstein Tensor
     */
    public GeneralFunction[][] einsteinTensor() {
        if (einsteinTensor == null)
            calculateEinsteinTensor();
        return einsteinTensor;
    }

    /**
     * Gets the line element of the the {@link Space}
     * @return the line element
     */
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

    /**
     * Gets the volume element of the the {@link Space}
     * @return the volume element
     */
    public GeneralFunction volumeElement() {
        if (metric.isDiagonal)
            return sqrt(determinantDiagonalMatrix(metric.matrix)).simplify();
        else
            return sqrt(determinant(metric.matrix)).simplify();
    }

    private void calculateChristoffelConnection() {
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
                    System.out.println(variableStrings[μ] + " " + variableStrings[σ] + " " + variableStrings[ν] + " : " + christoffelConnection[μ][σ][ν].toString());
                }
            }
        }
        this.christoffelConnection = christoffelConnection;
    }

    private void calculateRiemannTensor() {
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
                        System.out.println(variableStrings[λ] + " " + variableStrings[ρ] + " " + variableStrings[μ] + " " + variableStrings[ν] + " : " + riemannTensor[λ][ρ][μ][ν].toString());
                    }
                }
            }
        }

        this.riemannTensor = riemannTensor;

    }

    private void calculateRicciTensor() {
        GeneralFunction[][] ricciTensor = new GeneralFunction[dim][dim];
        GeneralFunction[][][][] R = riemannTensor();
        for (int ρ = 0; ρ < dim; ρ++) {
            for (int ν = 0; ν < dim; ν++) {

                GeneralFunction[] sum = new GeneralFunction[dim];

                for (int λ = 0; λ < dim; λ++) {
                    sum[λ] = R[λ][ρ][λ][ν];
                }

                ricciTensor[ρ][ν] = new Sum(sum).simplify();
                System.out.println(variableStrings[ρ] + " " + variableStrings[ν] + " : " + ricciTensor[ρ][ν].toString());

            }
        }

        this.ricciTensor = ricciTensor;
    }

    private void calculateRicciScalar() {
        GeneralFunction[][] R = ricciTensor();

        GeneralFunction[] sum1 = new GeneralFunction[dim];
        for (int ν = 0; ν < dim; ν++) {
            GeneralFunction[] sum2 = new GeneralFunction[dim];
            for (int μ = 0; μ < dim; μ++) {
                sum2[μ] = new Product(inverseMetric.matrix[ν][μ], R[μ][ν]);
            }
            sum1[ν] = new Sum(sum2).simplify();
        }
        this.ricciScalar = new Sum(sum1).simplify();
    }


    private void calculateEinsteinTensor() {
        GeneralFunction[][] G = ricciTensor();
        GeneralFunction R = ricciScalar();

        for (int μ = 0; μ < dim; μ++) {
            for (int ν = 0; ν < dim; ν++) {
                G[μ][ν] = new Sum(G[μ][ν], negative(new Product(new Constant(0.5), metric.matrix[μ][ν], R))).simplify();
                System.out.println(variableStrings[μ] + " " +variableStrings[ν] + " : " + G[μ][ν].toString());
            }
        }

        this.einsteinTensor = G;
    }


}
