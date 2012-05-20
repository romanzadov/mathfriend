package tree.simple;

import tree.compound.CompoundTerm;
import display.Rectangle;

import java.util.EnumSet;
import java.util.Set;

public class Constants extends Number{

    public static Set<Constant> getAllConstants() {
        return EnumSet.allOf(Constant.class);
    }

    private Constant constant;

    private Constants(double a) {
        super(a);
    }

    public Constants(Constant constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        return constant.getName();
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

        public Rectangle giverect(CompoundTerm tr) {
            Rectangle a = new Rectangle();
            if (value == Math.E) {
                a.setHeight(1);
                a.setWidth(1);
                //	tr.toDraw = "e";
                tr.setContainer(a);
            }
            if (value == Math.PI) {
                a.setHeight(1);
                a.setWidth(2);
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
