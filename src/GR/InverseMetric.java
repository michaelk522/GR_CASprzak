package GR;

import functions.GeneralFunction;

public class InverseMetric extends Tensor {
    public GeneralFunction[][] matrix;
    public boolean isDiagonal;


    public InverseMetric(Space space, GeneralFunction[][] inverseMetric) {
        super(space, 2, 0);
        this.matrix = inverseMetric;
        isDiagonal = false;
    }

    public InverseMetric(Space space, GeneralFunction[][] metric, boolean isDiagonal) {
        super(space, 0, 2);
        this.matrix = metric;
        this.isDiagonal = isDiagonal;
    }
}
