package parse;

import java.util.ArrayList;
import java.util.EnumSet;

import tree.operators.Operator;
import tree.operators.Parens;
import tree.simple.Constant;
import tree.simple.Number;

public class PreSimpleUtil {

    ArrayList<PreSimpleTerm> preSimpleTerms = new ArrayList<PreSimpleTerm>();
    public boolean done = false;

    public ArrayList<PreSimpleTerm> simplify(ArrayList<Character> characters) {


        for (int i = 0; i < characters.size(); i++) {
            done = false;

            if (!done) {
                i = isOperator(i, characters);
            }
            if (!done) {
                i = isConstant(i, characters);
            }
            if (!done) {
                i = isVariable(i, characters);
            }
            if (!done) {
                i = isNumber(i, characters);
            }
            if (!done) {
                i = isParentheses(i, characters);
            }
            if (!done) {
                throw new RuntimeException("Unable to parse: "+characters + " at index: "+i);
            }

        }

        return preSimpleTerms;
    }

    private int isConstant(int i, ArrayList<Character> formula) {

        int out = i;

        for(Constant constant:  EnumSet.allOf(Constant.class)) {
            char[] name = constant.getName().toCharArray();
            int falses = 0;

            if (formula.size() - i >= name.length) {

                for (int k = 0; k < name.length; k++) {

                    String a = String.valueOf(formula.get(i + k));
                    String b = String.valueOf(name[k]);

                    if (!a.equalsIgnoreCase(b)) {
                        falses++;
                    }
                }

                if (falses == 0) {
                    out = i + name.length;
                    PreSimpleTerm preSimpleConstant = new PreSimpleTerm(formula.subList(i, out), PreSimpleTerm.Type.CONSTANT);
                    preSimpleTerms.add(preSimpleConstant);
                    done = true;
                }

            }

        }

        return out;
    }

    private int isOperator(int i, ArrayList<Character> formula) {

        int out = i;

        for (int j = 0; j < Operator.KNOWNFUNCTIONS.length; j++) {
            String con = Operator.KNOWNFUNCTIONS[j];
            char[] cons = con.toCharArray();
            int falses = 0;

            if (formula.size() - i >= cons.length) {

                for (int k = 0; k < cons.length; k++) {

                    String a = String.valueOf(formula.get(i + k));
                    String b = String.valueOf(cons[k]);

                    if (!a.equals(b)) {
                        falses++;
                    }
                }

                if (falses == 0) {
                    out = i + con.length();
                    PreSimpleTerm preSimpleOperator = new PreSimpleTerm(formula.subList(i, out), PreSimpleTerm.Type.FUNCTION);
                    preSimpleTerms.add(preSimpleOperator);
                    done = true;

                }

            }


        }
        return out;
    }

    private int isVariable(int i, ArrayList<Character> formula) {
        int out = i;
        if (Character.isLetter(formula.get(i))) {
            out = i + 1;
            PreSimpleTerm preSimpleVariable = new PreSimpleTerm(formula.subList(i, out), PreSimpleTerm.Type.FUNCTION);
            preSimpleTerms.add(preSimpleVariable);
            done = true;

        }
        return out;
    }

    private int isNumber(int i, ArrayList<Character> formula) {
        int out = i;
        int numlength = 0;
        boolean numberdone = false;
        while (numberdone == false && out < formula.size()) {
            if (Character.isDigit(formula.get(out)) == true
                    || Character.valueOf(formula.get(out)) == ','
                    || Character.valueOf(formula.get(out)) == '.'
                    ) {
                numlength++;
                out++;
            } else {
                numberdone = true;
            }
        }
        if (numlength > 0) {
            PreSimpleTerm preSimpleNumber = new PreSimpleTerm(formula.subList(i, out), PreSimpleTerm.Type.NUMBER);
            preSimpleTerms.add(preSimpleNumber);
            done = true;
        }


        return out;
    }

    private int isParentheses(int i, ArrayList<Character> formula) {
        int out = i;
        if (Character.valueOf(formula.get(i)) == '(' || Character.valueOf(formula.get(i)) == ')') {
            out = i + 1;
            PreSimpleTerm preSimpleParentheses = new PreSimpleTerm(formula.subList(i, i+1), PreSimpleTerm.Type.PARENTHESES);
            preSimpleTerms.add(preSimpleParentheses);
            done = true;
        }
        return out;

    }


}
