package parse;

import java.util.ArrayList;

import tree.operators.AdvancedOperator;
import tree.operators.Operator;
import tree.operators.Parens;
import tree.simple.Constant;
import tree.simple.Number;
import tree.simple.SimpleTerm;
import tree.simple.variable;

public class PreSimple {

    ArrayList<SimpleTerm> simp = new ArrayList<SimpleTerm>();
    public boolean done = false;

    public ArrayList<SimpleTerm> simplify(ArrayList<Character> formula) {


        for (int i = 0; i < formula.size(); i++) {
            done = false;

            if (done == false) {
                i = isoperator(i, formula);
            }
            if (done == false) {
                i = isconstant(i, formula);
            }
            if (done == false) {
                i = isvariable(i, formula);
            }
            if (done == false) {
                i = isnumber(i, formula);
            }
            if (done == false) {
                i = isparen(i, formula);
            }
            //	isother(i,formula);

        }


        return simp;
    }

    public int isconstant(int i, ArrayList<Character> formula) {


        int out = i;

        for (int j = 0; j < Constant.CONSTANTS.length; j++) {
            String con = Constant.CONSTANTS[j];
            char[] cons = con.toCharArray();
            int falses = 0;

            if (formula.size() - i >= cons.length) {

                for (int k = 0; k < cons.length; k++) {

                    String a = String.valueOf(formula.get(i + k));
                    String b = String.valueOf(cons[k]);

                    if (a.equals(b) == false) {
                        falses++;
                    }
                }

                if (falses == 0) {
                    Constant thiscon = new Constant(Constant.values[j]);
                    thiscon.value = Constant.values[j];
                    thiscon.charpos = i;
                    simp.add(thiscon);
                    out = i + cons.length - 1;
                    j = Constant.CONSTANTS.length;
                    done = true;
                }

            }


        }
        return out;
    }

    public int isoperator(int i, ArrayList<Character> formula) {

        //operator op = new operator();
        int out = i;

        for (int j = 0; j < Operator.KNOWNFUNCTIONS.length; j++) {
            String con = Operator.KNOWNFUNCTIONS[j];
            char[] cons = con.toCharArray();
            int falses = 0;

            if (formula.size() - i >= cons.length) {

                for (int k = 0; k < cons.length; k++) {

                    String a = String.valueOf(formula.get(i + k));
                    String b = String.valueOf(cons[k]);

                    if (a.equals(b) == false) {
                        falses++;
                    }
                }

                if (falses == 0) {
                    AdvancedOperator thisop = new AdvancedOperator();

                    thisop.thisvalue = Operator.KNOWNFUNCTIONS[j];
                    thisop.charpos = i;
                    simp.add(thisop);
                    out = i + cons.length - 1;
                    j = Operator.KNOWNFUNCTIONS.length;
                    done = true;

                }

            }


        }
        return out;
    }

    public int isvariable(int i, ArrayList<Character> formula) {
        int out = i;
        if (Character.isLetter(formula.get(i)) == true) {
            variable var = new variable();
            var.value = String.valueOf(formula.get(i));
            simp.add(var);
            done = true;

        }
        return out;
    }

    public int isnumber(int i, ArrayList<Character> formula) {
        int ans = i;
        int numlength = 0;
        boolean numberdone = false;
        while (numberdone == false && ans < formula.size()) {
            if (Character.isDigit(formula.get(ans)) == true
                    || Character.valueOf(formula.get(ans)) == ','
                    || Character.valueOf(formula.get(ans)) == '.'
                    ) {
                numlength++;
                ans++;
            } else {
                numberdone = true;
            }
        }
        if (numlength > 0) {

            String number = new String();
            for (int k = 0; k < numlength; k++) {
                number = number + formula.get(i + k);
            }
            double n = Double.parseDouble(number);
            Number numobj = new Number(n);
            numobj.value = n;
            numobj.charpos = i;
            simp.add(numobj);
            done = true;
            ans = i + numlength - 1;
        }


        return ans;
    }

    public int isparen(int i, ArrayList<Character> formula) {
        int out = i;
        if (Character.valueOf(formula.get(i)) == '(') {
            Parens lp = new Parens();
            lp.setvalue('(');
            simp.add(lp);
            done = true;
        } else if (Character.valueOf(formula.get(i)) == ')') {
            Parens rp = new Parens();
            rp.setvalue(')');
            simp.add(rp);
            done = true;
        } else {
        }
        return out;

    }


}
