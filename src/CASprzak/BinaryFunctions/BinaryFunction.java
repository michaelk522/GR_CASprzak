package CASprzak.BinaryFunctions;
import CASprzak.Differentiable;
import CASprzak.Function;

public abstract class BinaryFunction extends Function {
    protected Function function1;
    protected Function function2;

    public BinaryFunction(Function function1, Function function2) {
        this.function1 = function1;
        this.function2 = function2;
    }
}