package GR;

public class Tensor {
    int tangentSpaces;
    int cotangentSpaces;
    Space space;

    public Tensor(Space space, int tangentSpaces, int cotangentSpaces) {
        this.space = space;
        this.tangentSpaces = tangentSpaces;
        this.cotangentSpaces = cotangentSpaces;
    }
}
