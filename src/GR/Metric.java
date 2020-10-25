package GR;

import functions.GeneralFunction;

public class Metric extends Tensor {
    public GeneralFunction[][] matrix;
    public boolean isDiagonal;

    public Metric(Space space, GeneralFunction[][] metric) {
        super(space, 0, 2);
        this.matrix = metric;
        isDiagonal = false;
    }

    public Metric(Space space, GeneralFunction[][] metric, boolean isDiagonal) {
        super(space, 0, 2);
        this.matrix = metric;
        this.isDiagonal = isDiagonal;
    }
}
