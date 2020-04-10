package CASprzak;

import java.util.*;

public class CASUI {
	public static final boolean simplifyFunctionsOfConstants = true;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in.useDelimiter("\n");

		System.out.println("What are your variables? Separate with spaces.");
		String[] varsTemp = in.next().split(",\\s*");
		char[] vars = new char[varsTemp.length];
		for (int i = 0; i < vars.length; i++) vars[i] = varsTemp[i].charAt(0);

		System.out.println("Enter your function to be stored:");
		String raw = in.next();

		System.out.println("What are your inputs? Separate with commas, and order them with your variables.");
		String[] visTemp = in.next().split(",\\s*");
		double[] vis = Arrays.stream(visTemp).mapToDouble(ConstantEvaluator::getConstant).toArray();
		System.out.println("Processing...");

		PreProcessor preProcessor = new PreProcessor(vars);
		SingleVariableSolver solver = new SingleVariableSolver();
		Parser parser = new Parser(vars);

		Function curFun = parser.parse(preProcessor.toPostfix(raw));
		System.out.println("Here is your parsed function: " + curFun);
		System.out.println("Here is the simplified toString of your function: " + curFun.simplifyTimes(10));
		System.out.println("Here is your output: " + curFun.evaluate(vis));
		System.out.println("Here is the derivative, simplified once:");
		System.out.println(curFun.getSimplifiedDerivative(0));
		System.out.println("Here is the derivative, simplified completely:");
		System.out.println(curFun.getSimplifiedDerivative(0).simplifyTimes(10));
		System.out.println("Here is the derivative, evaluated:");
		System.out.println(curFun.getSimplifiedDerivative(0).simplifyTimes(10).evaluate(vis));
		System.out.println("Here is a zero for the expression");
		System.out.println(solver.getSolutionPoint(curFun, -10));
		System.out.println("Here are the zeros for the expression");
		System.out.println(Arrays.toString(solver.getSolutionsRange(curFun, -10, 10)));

	}
}

