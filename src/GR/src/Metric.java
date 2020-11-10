package GR.src;

import GR.GR;
import functions.GeneralFunction;

/**
 * This class contains the the matrix of an {@code Metric} and whether or not it is diagonal.
 */
public class Metric{
    /**
     * the matrix representation of the {@code Metric}
     */
    public GeneralFunction[][] matrix;
    /**
     * Denotes whether or not the matrix is diagonal
     */
    public boolean isDiagonal;

    /**
     * Defines a new {@link Metric}
     * @param metric the matrix of the {@code Metric}
     */
    public Metric(GeneralFunction[][] metric) {
        this.matrix = metric;
        isDiagonal = false;
    }

    /**
     * Defines a new {@link Metric}
     * @param metric the matrix of the {@code Metric}
     * @param isDiagonal denotes whether or not the matrix is diagonal
     */
    public Metric(GeneralFunction[][] metric, boolean isDiagonal) {
        this.matrix = metric;
        this.isDiagonal = isDiagonal;
    }
}
