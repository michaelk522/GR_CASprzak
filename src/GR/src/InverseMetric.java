package GR.src;

import GR.GR;
import functions.GeneralFunction;

public class InverseMetric{
    public GeneralFunction[][] matrix;
    public boolean isDiagonal;


    public InverseMetric(GeneralFunction[][] inverseMetric) {
        this.matrix = inverseMetric;
        isDiagonal = false;
    }

    public InverseMetric(GeneralFunction[][] metric, boolean isDiagonal) {
        this.matrix = metric;
        this.isDiagonal = isDiagonal;
    }
}
