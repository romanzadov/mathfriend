package tree.simple;

import tree.Term;
import display.rectangle;

import java.util.EnumSet;
import java.util.Set;

public class Constants extends Number{

    public static Set<Constant> getAllConstants() {
        return EnumSet.allOf(Constant.class);
    }

    private Constants(double a) {
        super(a);
    }

    

    public enum Constant {
        PI("pi", Math.PI),
        E("e", Math.E);

        private String name;
        private double value;

        Constant(String name, double value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public double getValue() {
            return value;
        }

        public rectangle giverect(Term tr) {
            rectangle a = new rectangle();
            if (value == Math.E) {
                a.height = 1;
                a.width = 1;
                //	tr.toDraw = "e";
                tr.setContainer(a);
            }
            if (value == Math.PI) {
                a.height = 1;
                a.width = 2;
                //	tr.toDraw = "pi";
                tr.setContainer(a);
            }
            return a;
        }

        public boolean isoperator() {
            return false;
        }
    }

}
