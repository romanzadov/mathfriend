package parse;

import java.util.ArrayList;
import java.util.Arrays;
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
                throw new RuntimeException("Unable to parse: " + characters + " at index: " + i);
            }

        }

        setExplicitFunctions(preSimpleTerms);

        return preSimpleTerms;
    }

    private int isConstant(int i, ArrayList<Character> formula) {

        int out = i;

        for (Constant constant : EnumSet.allOf(Constant.class)) {
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
            PreSimpleTerm preSimpleParentheses = new PreSimpleTerm(formula.subList(i, i + 1), PreSimpleTerm.Type.PARENTHESES);
            preSimpleTerms.add(preSimpleParentheses);
            done = true;
        }
        return out;

    }

    private void setExplicitFunctions(ArrayList<PreSimpleTerm> formula) {
        for (PreSimpleTerm preSimpleTerm : formula) {
            if (preSimpleTerm.getType().equals(PreSimpleTerm.Type.FUNCTION)) {

                for(PreSimpleTerm.FunctionType functionType: EnumSet.allOf(PreSimpleTerm.FunctionType.class)) {

                    if(!functionType.equals(PreSimpleTerm.FunctionType.NEGATIVE)) {
                        if (preSimpleTerm.toString().equals(functionType.getRepresentation())) {
                            preSimpleTerm.setFunctionType(functionType);
                        }
                    }
                }
            }
        }

        //reset minuses to negatives
        for (PreSimpleTerm preSimpleTerm : formula) {
            if (preSimpleTerm.getFunctionType().equals(PreSimpleTerm.FunctionType.MINUS)) {

                int i = preSimpleTerms.indexOf(preSimpleTerm);

                if (i == 0 || preSimpleTerms.get(i - 1).getFunctionType().equals(PreSimpleTerm.FunctionType.EQUALITY)
                        || preSimpleTerms.get(i - 1).toString().equals("(")
                        || preSimpleTerms.get(i - 1).getType().equals(PreSimpleTerm.Type.FUNCTION)) {

                    preSimpleTerm.setFunctionType(PreSimpleTerm.FunctionType.NEGATIVE);

                    //add parens around negative
                    //unless there's an exponent afterwards
                    /*boolean add = true;

                    if (preSimpleTerms.size() > i + 2) {
                        if (preSimpleTerms.get(i + 2).getFunctionType().equals(PreSimpleTerm.FunctionType.EXPONENT)) {
                            add = false;
                        }

                    }
                    if (i > 0) {
                        if (preSimpleTerms.get(i - 1).getFunctionType().equals(PreSimpleTerm.FunctionType.EXPONENT)) {
                            add = true;
                        }
                    }

                    if (simp.size() > i + 2) {
                        if (simp.get(i + 2) instanceof Parens) {
                            //if parens, find end
                            int end = 0;
                            ArrayList<int[]> pans = ParenthesisUtil.getParenthesisGroups(simp);
                            for (int j = 0; j < pans.size(); j++) {
                                if (pans.get(j)[0] == i + 2) {
                                    end = pans.get(j)[1];
                                }
                            }
                            if (end != 0) {
                                if (simp.size() > end + 1) {
                                    //the the one after parens is an exponent
                                    //check that the one before isn't
                                    if (simp.get(end + 1) instanceof Exponent) {
                                        add = false;
                                    }
                                }
                            }
                            if (i - 1 > 0) {
                                if (simp.get(i - 1) instanceof Exponent) {
                                    add = true;
                                }
                            }
                        }
                    }

                    if (add) {
                        simp = addParensAroundNegatives(simp, pns, i);
                    }*/

                }
            }


        }

        //add invisible multiplication
/*        for (int i = 0; i < preSimpleTerms.size() - 1; i++) {
            if (preSimpleTerms.get(i).getRightMultiply() == true && preSimpleTerms.get(i + 1).getLeftMultiply() == true) {
                PreSimpleTerm multiply = new PreSimpleTerm(Arrays.asList('*'), PreSimpleTerm.Type.FUNCTION);
                multiply.setFunctionType(PreSimpleTerm.FunctionType.TIMES);
                preSimpleTerms.add(i + 1, multiply);
            }

        }*/

    }

}
