package tree.simple;

import java.util.EnumSet;
import java.util.Set;

public class Constants extends Number{

    public static Set<Constant> getAllConstants() {
        return EnumSet.allOf(Constant.class);
    }

    private Constant constant;

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

        public boolean isoperator() {
            return false;
        }
    }

}
