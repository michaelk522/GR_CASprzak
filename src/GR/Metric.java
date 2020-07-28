package GR;

import functions.GeneralFunction;

public class Metric extends Tensor {
    public GeneralFunction[][] matrix;

    public Metric(Space space, GeneralFunction[][] metric) {
        super(space, 0, 2);
        this.matrix = metric;
    }
}
