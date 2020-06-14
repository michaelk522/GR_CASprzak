package output.unitary;

import output.OutputFunction;

public class OutputAbs extends OutputUnitary {

	public OutputAbs(OutputFunction operand) {
		super(null, operand);
	}

	public String toString() {
		return "|" + operand.toString() + "|";
	}

}
