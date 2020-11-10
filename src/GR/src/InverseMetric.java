package GR.src;

import GR.GR;
import functions.GeneralFunction;

/**
 * This class contains the the matrix of an {@code InverseMetric} and whether or not it is diagonal.
 */
public class InverseMetric{
    /**
     * the matrix representation of the {@code InverseMetric}
     */
    public GeneralFunction[][] matrix;
    /**
     * Denotes whether or not the matrix is diagonal
     */
    public boolean isDiagonal;

    /**
     * Defines a new {@link InverseMetric}
     * @param inverseMetric the matrix of the {@code InverseMetric}
     */
    public InverseMetric(GeneralFunction[][] inverseMetric) {
        this.matrix = inverseMetric;
        isDiagonal = false;
    }

    /**
     * Defines a new {@link InverseMetric}
     * @param inverseMetric the matrix of the {@code InverseMetric}
     * @param isDiagonal denotes whether or not the matrix is diagonal
     */
    public InverseMetric(GeneralFunction[][] inverseMetric, boolean isDiagonal) {
        this.matrix = inverseMetric;
        this.isDiagonal = isDiagonal;
    }
}
