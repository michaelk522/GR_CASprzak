package GR;

import functions.GeneralFunction;
import functions.endpoint.Variable;
import tools.DefaultFunctions;

import java.util.Arrays;

public class Space {
    public int dim;
    public String[] variableStrings;
    public Variable[] variables;
    public GeneralFunction[][] metric;
    public GeneralFunction[][] inverseMetric;

    public Space(String... variableNames) {
        dim = variableNames.length;
        variableStrings = variableNames;
        variables = Arrays.stream(variableNames).map(Variable::new).toArray(Variable[]::new);
    }

    public void defMetric(GeneralFunction... x) {
        if (x.length != dim)
            throw new IllegalArgumentException("Expected " + dim + " functions but instead got " + x.length + ".");
        for (int i = 0; i < x.length; i++) {
            metric[i][i] = x[i].simplify();
            inverseMetric[i][i] = DefaultFunctions.reciprocal(x[i]).simplify();
        }
    }


}
