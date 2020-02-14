package CASprzak;

import java.util.*;

public class Parser {
  public static final String[] operations2 = {"^", "*", "/", "+", "-", "logb"};
  public static final String[] operations1 = {"sin", "cos", "tan", "log", "ln", "sqrt", "exp", "sinh", "cosh", "tanh", "csc", "sec", "cot", "asin", "acos", "atan"};

  private char[] variables = {};

  public boolean isAnOperator1(String input) {
    for (String x : operations1) {
      if(x.equals(input)) return true;
    }
    return false;
  }

  public boolean isAnOperator2(String input) {
    for (String x : operations2) {
      if(x.equals(input)) return true;
    }
    return false;
  }

  public void setVariables(char[] variables) {
    this.variables = variables;
  }

  public int getVarID(char variable) {
    for (int i = 0; i < variables.length; i++) if (variables[i] == v) {
      return i;
    }
  }

  public Function parse(String[] postfix) throws Exception {

    FunctionMaker functionMaker = new FunctionMaker();
    Stack<Function> functionStack = new Stack<Function>();
    outer: for (String token : postfix) {
      if (!isAnOperator1(token) && !isAnOperator2(token)) {
        try {
          functionStack.push(functionMaker.constant(Double.parseDouble(token)));
        } catch (Exception e) {
          if (token.length() > 1) System.out.println(token + " is not a valid function.");
          char v = token.charAt(0);
          functionStack.push(functionMaker.variable(getVarID(v), new char[]{v}));
        }
      } else if (isAnOperator2(token)) {
        Function a = functionStack.pop();
        Function b = functionStack.pop();
        functionStack.push(functionMaker.find2(i, a, b));
      } else if (isAnOperator1(token)) {
        Function c = functionStack.pop();
        functionStack.push(functionMaker.find1(i, c));
      }
    }
    if (functionStack.size() != 1) throw new IndexOutOfBoundsException("functionStack size is " + functionStack.size());
    return functionStack.pop();
  }

}
