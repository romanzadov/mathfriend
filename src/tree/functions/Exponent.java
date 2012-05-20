package tree.functions;

import display.Rectangle;
import representTerms.Image;
import tree.compound.CompoundTerm;

public class Exponent extends Function {

    public Exponent() {

        inputs = 2;
        invertible = true;
        commutative = false;
        distributive = false;
        associative = false;
        identity = 1;

    }

    @Override
    public String toString() {
        return "^";
    }


    @Override
    public CompoundTerm simpleOperation(CompoundTerm term) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double function(double a, double b) {
        double c = Math.pow(a, b);
        return c;
    }

    @Override
    public Image inTermMoves(Image im, CompoundTerm sel,
                             int IntermIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Image overEqualsMoves(Image im,
                                 CompoundTerm sel, int IntermIndex, double xsel) {
        // TODO Auto-generated method stub
        return null;
    }

}
