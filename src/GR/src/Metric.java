package GR.src;

import GR.GR;
import functions.GeneralFunction;

public class Metric{
    public GeneralFunction[][] matrix;
    public boolean isDiagonal;

    public Metric(GeneralFunction[][] metric) {
        this.matrix = metric;
        isDiagonal = false;
    }

    public Metric(GeneralFunction[][] metric, boolean isDiagonal) {
        this.matrix = metric;
        this.isDiagonal = isDiagonal;
    }
}
