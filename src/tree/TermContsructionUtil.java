package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parse.ParenthesisUtil;
import parse.PreSimpleTerm;
import tree.functions.*;


public class TermContsructionUtil {

    public static Class<? extends Function> getHighestPriorityFunction(List<PreSimpleTerm> preSimpleTerms) {
        Class<? extends Function> primaryPreFunction = null;
        int order = Integer.MAX_VALUE;
        Map<Integer, Integer> parentheses = ParenthesisUtil.getParenthesisGroups(preSimpleTerms);

        for (int i = 0; i < preSimpleTerms.size(); i++) {
            PreSimpleTerm preSimpleTerm = preSimpleTerms.get(i);
            if (preSimpleTerm.getType().equals(PreSimpleTerm.Type.FUNCTION)) {

                if (Function.getOrderOfOperation(preSimpleTerm) < order) {
                    primaryPreFunction = preSimpleTerm.getFunctionType().getFunction();
                    order = Function.getOrderOfOperation(preSimpleTerm);
                }
            }

            //check for invisible multiplication
            if (hasInvisibleMultiplication(preSimpleTerm, preSimpleTerms)) {
                if (Function.getOrderOfOperation(Times.class) < order) {
                    primaryPreFunction = Times.class;
                    order = Function.getOrderOfOperation(Times.class);
                }
            }

            //don't look for operators inside parentheses
            else if (preSimpleTerm.getType().equals(PreSimpleTerm.Type.PARENTHESES)) {
                i = parentheses.get(i);
            }
        }

        return primaryPreFunction;
    }

    public static List<PreSimpleTermGrouping> getGroupings(List<PreSimpleTerm> preSimpleTerms, Class<? extends Function> function) {
        List<PreSimpleTermGrouping> groupings = new ArrayList<PreSimpleTermGrouping>();

        List<Integer> groupEndpoints = new ArrayList<Integer>();

        for (PreSimpleTerm preSimpleTerm : preSimpleTerms) {
            int breakPoint = -1;
            boolean breakPointFound = false;
            if (PreSimpleTerm.Type.FUNCTION.equals(preSimpleTerm.getType()) && function.equals(preSimpleTerm.getFunctionType().getFunction())) {
                breakPointFound = true;
                breakPoint = preSimpleTerms.indexOf(preSimpleTerm);
            }
            //if our function is multiplication, check for implicit multiplications like 2x
            if (function == Times.class && !breakPointFound) {
                breakPointFound = hasInvisibleMultiplication(preSimpleTerm, preSimpleTerms);
                breakPoint = preSimpleTerms.indexOf(preSimpleTerm) + 1;
            }

            if (breakPointFound || preSimpleTerms.size() == preSimpleTerms.indexOf(preSimpleTerm)) {
                groupEndpoints.add(breakPoint);
            }
        }

        for (Integer groupEnd : groupEndpoints) {
            List<PreSimpleTerm> group = new ArrayList<PreSimpleTerm>();
            if (groupEndpoints.indexOf(groupEnd) == 0) {
                group = preSimpleTerms.subList(0, groupEnd);
            } else {
                group = preSimpleTerms.subList(groupEndpoints.get(groupEndpoints.indexOf(groupEnd) - 1), groupEnd);
            }

            groupings.add(getPreSimpleTermGrouping(group));
        }

        int lastBreakpoint = groupEndpoints.get(groupEndpoints.size() - 1);
        int endOfList =  preSimpleTerms.size();
        groupings.add(getPreSimpleTermGrouping(preSimpleTerms.subList(lastBreakpoint, endOfList)));

        return groupings;
    }

    private static PreSimpleTermGrouping getPreSimpleTermGrouping(List<PreSimpleTerm> group) {
        boolean isNegative = false;
        boolean isInverse = false;
        boolean hasParentheses = false;
        boolean ignoreFirst = false;
        if (group.size() > 1) {
            PreSimpleTerm firstTerm = group.get(0);
            PreSimpleTerm secondTerm = group.get(1);
            PreSimpleTerm lastTerm = group.get(group.size() - 1);
            if (PreSimpleTerm.FunctionType.MINUS.equals(firstTerm.getFunctionType()) || PreSimpleTerm.FunctionType.NEGATIVE.equals(firstTerm.getFunctionType())) {
                if(Function.getOrderOfOperation(getHighestPriorityFunction(group)) > Function.getOrderOfOperation(Plus.class)) {
                    isNegative = true;
                    group = remove(group, firstTerm);
                    if (PreSimpleTerm.Type.PARENTHESES.equals(secondTerm.getType()) && PreSimpleTerm.Type.PARENTHESES.equals(lastTerm.getType())) {
                        hasParentheses = true;
                        group = remove(group, secondTerm);
                        group = remove(group, lastTerm);
                    }
                }

            } else if (PreSimpleTerm.FunctionType.DIVIDE.equals(firstTerm.getFunctionType())) {
                isInverse = true;
                group = remove(group, firstTerm);
                if (PreSimpleTerm.Type.PARENTHESES.equals(secondTerm.getType()) && PreSimpleTerm.Type.PARENTHESES.equals(lastTerm.getType())) {
                    hasParentheses = false;//example: 2/(3x-4).  These are probably parentheses for parsing, not for the equation. I will remove them
                    group = remove(group, secondTerm);
                    group = remove(group, lastTerm);
                }
            } else if (PreSimpleTerm.Type.PARENTHESES.equals(firstTerm.getType()) && PreSimpleTerm.Type.PARENTHESES.equals(lastTerm.getType())) {
                hasParentheses = true;
                group = remove(group, firstTerm);
                group = remove(group, lastTerm);
            } else if (PreSimpleTerm.Type.FUNCTION.equals(firstTerm.getType()) && firstTerm.getFunctionType().getFunction() == AdvancedFunction.class) {
                group = remove(group, firstTerm);
                if (PreSimpleTerm.Type.PARENTHESES.equals(secondTerm.getType()) && PreSimpleTerm.Type.PARENTHESES.equals(lastTerm.getType())) {
                    group = remove(group, secondTerm);
                    group = remove(group, lastTerm);
                } else {
                    throw new RuntimeException("advanced function without a parentheses afterwards");
                }
            } else {
                //trim operators
                if (PreSimpleTerm.Type.FUNCTION.equals(firstTerm.getType())) {
                    ignoreFirst = true;
                }
            }

        }
        if(ignoreFirst) {
            return new PreSimpleTermGrouping(group.subList(1, group.size()), isNegative, isInverse, hasParentheses);
        } else {
            return new PreSimpleTermGrouping(group, isNegative, isInverse, hasParentheses);
        }

    }

    private static boolean hasInvisibleMultiplication(PreSimpleTerm preSimpleTerm, List<PreSimpleTerm> preSimpleTerms) {
        int index = preSimpleTerms.indexOf(preSimpleTerm);

        if (index == preSimpleTerms.size() - 1) {
            return false;
        } else {
            PreSimpleTerm nextTerm = preSimpleTerms.get(index + 1);
            return preSimpleTerm.isRightMultiplied() && nextTerm.isLeftMultiplied();
        }
    }

    private static List<PreSimpleTerm> remove(List<PreSimpleTerm> group, PreSimpleTerm preSimpleTerm) {
        List<PreSimpleTerm> newGroup = new ArrayList<PreSimpleTerm>();
        for(PreSimpleTerm term: group) {
            if(!term.equals(preSimpleTerm)) {
                newGroup.add(term);
            }
        }
        return newGroup;
    }
}
