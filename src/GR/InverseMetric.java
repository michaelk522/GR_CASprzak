package GR;

import functions.GeneralFunction;

public class InverseMetric extends Tensor {
    public GeneralFunction[][] inverseMetric;


    public InverseMetric(Space space, GeneralFunction[][] inverseMetric) {
        super(space, 2, 0);
        this.inverseMetric = inverseMetric;
    }
}
