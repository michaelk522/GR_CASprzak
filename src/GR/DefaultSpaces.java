package GR;

import functions.GeneralFunction;
import functions.binary.Pow;
import functions.commutative.Product;
import functions.endpoint.Constant;
import functions.endpoint.Variable;
import functions.unitary.trig.normal.Sin;

public class DefaultSpaces {

    public static final Space cartesian2d = new Space(new String[]{"x", "y"}, new GeneralFunction[]{new Constant(1),
                                                                                                    new Constant(1)});

    public static final Space cartesian3d = new Space(new String[]{"x", "y", "z"}, new GeneralFunction[]{new Constant(1),
                                                                                                         new Constant(1),
                                                                                                         new Constant(1)});

    public static final Space polar = new Space(new String[]{"r", "θ"}, new GeneralFunction[]{new Constant(1),
                                                                                              new Pow(new Constant(2), new Variable("r"))});

    public static final Space cylindrical = new Space(new String[]{"r", "θ", "z"}, new GeneralFunction[]{new Constant(1),
                                                                                                         new Pow(new Constant(2), new Variable("r")),
                                                                                                         new Constant(1)});

    public static final Space spherical = new Space(new String[]{"r", "θ", "φ"}, new GeneralFunction[]{new Constant(1),
                                                                                                       new Pow(new Constant(2), new Variable("r")),
                                                                                                       new Pow(new Constant(2), new Product(new Variable("r"), new Sin(new Variable("θ"))))});

    public static final Space minkowski1d = new Space(new String[]{"t", "x"}, new GeneralFunction[]{new Constant(-1),
                                                                                                    new Constant(1)});

    public static final Space minkowski2d = new Space(new String[]{"t", "x", "y"}, new GeneralFunction[]{new Constant(-1),
                                                                                                         new Constant(1),
                                                                                                         new Constant(1)});

    public static final Space minkowski = new Space(new String[]{"t", "x", "y", "z"}, new GeneralFunction[]{new Constant(-1),
                                                                                                            new Constant(1),
                                                                                                            new Constant(1),
                                                                                                            new Constant(1)});
}
