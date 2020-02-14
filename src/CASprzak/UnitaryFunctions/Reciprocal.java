package CASprzak.UnitaryFunctions;

import CASprzak.BinaryFunctions.Pow;
import CASprzak.CommutativeFunctions.Multiply;
import CASprzak.Function;
import CASprzak.SpecialFunctions.Constant;

public class Reciprocal extends UnitaryFunction{
	public Reciprocal(Function function) {
		super(function);
	}

	public double evaluate(double[] variableValues) {
		return 1 / function.evaluate(variableValues);
	}
	public String toString() {
		return "("+ function.toString()+")^(-1)";
	}

	@Override
	public Function getDerivative(int varID) {
		return new Multiply(new Negative(function.getDerivative(varID)), new Reciprocal(new Pow(new Constant(2), function)));
	}
}