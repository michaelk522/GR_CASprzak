package GR;

import GR.src.Space;
import functions.GeneralFunction;
import functions.commutative.Product;
import functions.endpoint.Variable;
import functions.unitary.trig.normal.Sin;

import static tools.DefaultFunctions.*;

/**
 * {@link DefaultSpaces} contains many common variables and common geometries. This is for ease of use and also so that the same {@link Space} isn't re-instantiated when it doesnt need to be.
 */
@SuppressWarnings({"NonAsciiCharacters", "unused"})
public class DefaultSpaces {

    /**
     * A {@link Variable} representing the Gravitational Constant
     */
    public static final Variable G = new Variable("G");

    /**
     *  A {@link Variable} representing a mass
     */
    public static final Variable M = new Variable("M");

    /**
     *  A {@link Variable} representing the variable {@code t}
     */
    public static final Variable t = new Variable("t");

    /**
     *  A {@link Variable} representing the variable {@code x}
     */
    public static final Variable x = new Variable("x");

    /**
     *  A {@link Variable} representing the variable {@code y}
     */
    public static final Variable y = new Variable("y");

    /**
     *  A {@link Variable} representing the variable {@code z}
     */
    public static final Variable z = new Variable("z");

    /**
     *  A {@link Variable} representing the variable {@code r}
     */
    public static final Variable r = new Variable("r");

    /**
     *  A {@link Variable} representing the variable {@code θ}
     */
    public static final Variable θ = new Variable("θ");

    /**
     *  A {@link Variable} representing the variable {@code φ}
     */
    public static final Variable φ = new Variable("φ");

    /**
     *  A {@link Variable} representing the variable {@code ψ}
     */
    public static final Variable ψ = new Variable("ψ");

    /**
     * A {@link Variable} typically representing some collection of constants
     */
    public static final Variable r_s = new Variable("\\r_s");


    /**
     * A {@link Space} representing the 2D cartesian plane
     */
    public static final Space cartesian2d = new Space(new String[]{"x", "y"}, new GeneralFunction[]{
            ONE,
            ONE
    });

    /**
     * A {@link Space} representing the 3D cartesian space
     */
    public static final Space cartesian3d = new Space(new String[]{"x", "y", "z"}, new GeneralFunction[]{
            ONE,
            ONE,
            ONE
    });

    /**
     * A {@link Space} representing the 2D polar plane
     */
    public static final Space polar = new Space(new String[]{"r", "θ"}, new GeneralFunction[]{
            ONE,
            square(r)
    });

    /**
     * A {@link Space} representing 3D cylindrical coordinates
     */
    public static final Space cylindrical = new Space(new String[]{"r", "θ", "z"}, new GeneralFunction[]{
            ONE,
            square(r),
            ONE
    });

    /**
     * A {@link Space} representing 3D spherical coordinates
     */
    public static final Space spherical = new Space(new String[]{"r", "θ", "φ"}, new GeneralFunction[]{
            ONE,
            square(r),
            square(new Product(r, new Sin(θ)))
    });

    /**
     * A {@link Space} representing the 1D Minkowski Space
     */
    public static final Space minkowski1d = new Space(new String[]{"t", "x"}, new GeneralFunction[]{
            NEGATIVE_ONE,
            ONE
    });

    /**
     * A {@link Space} representing the 2D Minkowski Space
     */
    public static final Space minkowski2d = new Space(new String[]{"t", "x", "y"}, new GeneralFunction[]{
            NEGATIVE_ONE,
            ONE,
            ONE
    });

    /**
     * A {@link Space} representing the Minkowski Space
     */
    public static final Space minkowski = new Space(new String[]{"t", "x", "y", "z"}, new GeneralFunction[]{
            NEGATIVE_ONE,
            ONE,
            ONE,
            ONE
    });

    /**
     * A {@link Space} representing the surface of a sphere
     */
    public static final Space s2 = new Space(new String[]{"θ", "φ"}, new GeneralFunction[]{
            ONE,
            square(new Sin(θ))
    });

    /**
     * A {@link Space} representing the surface of a 3-sphere
     */
    public static final Space s3 = new Space(new String[]{"ψ", "θ", "φ"}, new GeneralFunction[]{
            ONE,
            square(new Sin(ψ)),
            square(new Product(new Sin(ψ), new Sin(θ)))
    });

    /**
     * A {@link Space} representing the Schwarzschild Metric
     */
    public static final Space schwarzschild = new Space(new String[]{"t", "r", "θ", "φ"}, new GeneralFunction[]{
            negative(  subtract(ONE, new Product(r_s, reciprocal(r)))),
            reciprocal(subtract(ONE, new Product(r_s, reciprocal(r)))),
            square(r),
            square(new Product(r, new Sin(θ)))
    });
}
