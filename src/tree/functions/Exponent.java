package tree.functions;

import display.rectangle;
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

    public rectangle giverect(CompoundTerm tr) {
        rectangle a = new rectangle();
        float xsofar = 0;
        float ysofar = 0;
        if (tr.isSimple()) {
            a = justexp(tr);
        } else {
            //check that all things are containered
    /*        for (int i = 0; i < tr.getChildren().size(); i++) {
                if (tr.getChildren().get(i).getContainer() == null) {
                    System.out.println("error: exponent was run on non-containered terms");
                }
            }


            xsofar = tr.getChildren().get(0).getContainer().width;
            ysofar = tr.getChildren().get(0).getContainer().height;

            for (int i = 2; i < tr.getChildren().size(); i += 2) {
                fontize ft = new fontize(tr.getChildren().get(i), .7);

            }

            for (int i = 2; i < tr.getChildren().size(); i += 2) {
                rectangle cn = tr.getChildren().get(i).getContainer();

                cn.bl.x = xsofar;
                cn.bl.y = ysofar;
                xsofar += cn.width;
                ysofar += cn.height / 2;
                if (i == tr.getChildren().size() - 1) {
                    ysofar += cn.height / 2;
                }
            }*/
        }
        a.width = xsofar;
        a.height = ysofar;
        return a;
    }

    public rectangle justexp(CompoundTerm tr) {
        rectangle a = new rectangle();
        tr.setContainer(a);
        return a;
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
