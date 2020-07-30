package GR;

import functions.GeneralFunction;
import functions.commutative.Product;
import functions.endpoint.Variable;
import functions.unitary.trig.normal.Sin;

import static tools.DefaultFunctions.*;

@SuppressWarnings("NonAsciiCharacters")
public class DefaultSpaces {
    public static final Variable G = new Variable("G");
    public static final Variable M = new Variable("M");
    public static final Variable t = new Variable("t");
    public static final Variable x = new Variable("x");
    public static final Variable y = new Variable("y");
    public static final Variable z = new Variable("z");
    public static final Variable r = new Variable("r");
    public static final Variable θ = new Variable("θ");
    public static final Variable φ = new Variable("φ");
    public static final Variable ψ = new Variable("ψ");




    public static final Space cartesian2d = new Space(new String[]{"x", "y"}, new GeneralFunction[]{
            ONE,
            ONE
    });

    public static final Space cartesian3d = new Space(new String[]{"x", "y", "z"}, new GeneralFunction[]{
            ONE,
            ONE,
            ONE
    });

    public static final Space polar = new Space(new String[]{"r", "θ"}, new GeneralFunction[]{
            ONE,
            square(r)
    });

    public static final Space cylindrical = new Space(new String[]{"r", "θ", "z"}, new GeneralFunction[]{
            ONE,
            square(r),
            ONE
    });

    public static final Space spherical = new Space(new String[]{"r", "θ", "φ"}, new GeneralFunction[]{
            ONE,
            square(r),
            square(new Product(r, new Sin(θ)))
    });

    public static final Space minkowski1d = new Space(new String[]{"t", "x"}, new GeneralFunction[]{
            NEGATIVE_ONE,
            ONE
    });

    public static final Space minkowski2d = new Space(new String[]{"t", "x", "y"}, new GeneralFunction[]{
            NEGATIVE_ONE,
            ONE,
            ONE
    });

    public static final Space minkowski = new Space(new String[]{"t", "x", "y", "z"}, new GeneralFunction[]{
            NEGATIVE_ONE,
            ONE,
            ONE,
            ONE
    });

    public static final Space s2 = new Space(new String[]{"θ", "φ"}, new GeneralFunction[]{
            ONE,
            square(new Sin(θ))
    });

    public static final Space s3 = new Space(new String[]{"ψ", "θ", "φ"}, new GeneralFunction[]{
            ONE,
            square(new Sin(ψ)),
            square(new Product(new Sin(ψ), new Sin(θ)))
    });

    public static final Space schwarzschildInSchwarzschildCoordinates = new Space(new String[]{"t", "r", "θ", "φ"}, new GeneralFunction[]{
            negative(  subtract(ONE, new Product(TWO, G, M, reciprocal(r)))),
            reciprocal(subtract(ONE, new Product(TWO, G, M, reciprocal(r)))),
            square(r),
            square(new Product(r, new Sin(θ)))
    });
}
