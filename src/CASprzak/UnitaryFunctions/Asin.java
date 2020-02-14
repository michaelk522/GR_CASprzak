package CASprzak.UnitaryFunctions;

import CASprzak.BinaryFunctions.Pow;
import CASprzak.CommutativeFunctions.Add;
import CASprzak.CommutativeFunctions.Multiply;
import CASprzak.Function;
import CASprzak.SpecialFunctions.Constant;

public class Asin extends UnitaryFunction {
    public Asin(Function function) {
        super(function);
    }

    @Override
    public String toString() {
        return "asin(" + function.toString() + ")";
    }

    @Override
    public Function getDerivative(int varID) {
        return new Multiply(function.getDerivative(varID), new Reciprocal(new Pow(new Constant(0.5), ( new Add(new Pow(new Constant(2), function), new Negative(new Constant(1)))))));
    }

    @Override
    public double evaluate(double[] variableValues) {
        return Math.asin(function.evaluate(variableValues));
    }
}