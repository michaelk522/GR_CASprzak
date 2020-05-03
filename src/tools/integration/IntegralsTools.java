package tools.integration;

import functions.Function;
import functions.commutative.Product;
import functions.special.Constant;
import tools.FunctionTools;
import tools.helperclasses.Pair;


public class IntegralsTools {

    /**
     * Strips a {@link Function} of any Constants and returns a {@link Pair} of the constant and the stripped Function
     * @param function The Function whose Constant is being Stripped
     * @return A {@link Pair} of the constant and the stripped Function
     */
    public static Pair<Double, Function> stripConstants(Function function) {
        if (function instanceof Product multiply) {
            Function[] terms = multiply.simplifyConstants().getFunctions();
            double constant = 1;
            Function[] termsWithConstantRemoved = terms;
            for (int i = 0; i < multiply.getFunctions().length; i++) {
                if (terms[i] instanceof Constant number) {
                    constant *= number.constant;
                    termsWithConstantRemoved = FunctionTools.removeFunctionAt(terms, i);
                }
            }
            if (termsWithConstantRemoved.length == 1)
                return new Pair<>(constant, termsWithConstantRemoved[0]);
            else
                return new Pair<>(constant, (new Product(termsWithConstantRemoved)).simplifyPull());
        } else {
            return new Pair<>(1.0, function);
        }
    }
}
