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
            else if (PreSimpleTerm.Type.PARENTHESES.equals(preSimpleTerm.getType())) {
                i = parentheses.get(i);
            }
        }

        return primaryPreFunction;
    }

    public static List<PreSimpleTermGrouping> getGroupings(List<PreSimpleTerm> preSimpleTerms, Class<? extends Function> function) {
        List<PreSimpleTermGrouping> groupings = new ArrayList<PreSimpleTermGrouping>();
        Map<Integer, Integer> parentheses = ParenthesisUtil.getParenthesisGroups(preSimpleTerms);
        List<Integer> groupEndpoints = new ArrayList<Integer>();

        for (int i = 0; i < preSimpleTerms.size(); i++) {
            int breakPoint = -1;
            PreSimpleTerm preSimpleTerm = preSimpleTerms.get(i);
            boolean breakPointFound = false;
            if (PreSimpleTerm.Type.PARENTHESES.equals(preSimpleTerm.getType())) {
                i = parentheses.get(i);
            } else if (preSimpleTerms.indexOf(preSimpleTerm) > 0 && PreSimpleTerm.Type.FUNCTION.equals(preSimpleTerm.getType()) && function.equals(preSimpleTerm.getFunctionType().getFunction())) {

                //ignore case of negative after multiplication
                int index = preSimpleTerms.indexOf(preSimpleTerm);
                PreSimpleTerm previous = null;
                if (index > 0) {
                    previous = preSimpleTerms.get(index - 1);
                }
                boolean ignore = false;
                if (previous != null && PreSimpleTerm.Type.FUNCTION.equals(previous.getType()) &&
                        PreSimpleTerm.FunctionType.TIMES.equals(previous.getFunctionType()) &&
                        PreSimpleTerm.FunctionType.NEGATIVE.equals(preSimpleTerm.getFunctionType())) {
                    ignore = true;
                }

                if (!ignore) {
                    breakPointFound = true;
                    breakPoint = preSimpleTerms.indexOf(preSimpleTerm);
                }

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

            groupings.add(getPreSimpleTermGrouping(group, function));
        }
        if (groupEndpoints.size() == 0) {
            groupings.add(getPreSimpleTermGrouping(preSimpleTerms, function));
        } else {
            int lastBreakpoint = groupEndpoints.get(groupEndpoints.size() - 1);
            int endOfList = preSimpleTerms.size();
            groupings.add(getPreSimpleTermGrouping(preSimpleTerms.subList(lastBreakpoint, endOfList), function));
        }

        return groupings;
    }

    private static PreSimpleTermGrouping getPreSimpleTermGrouping(List<PreSimpleTerm> group, Class<? extends Function> function) {
        boolean isNegative = false;
        boolean isInverse = false;
        boolean hasParentheses = false;

        if (PreSimpleTerm.Type.FUNCTION.equals(group.get(0).getType()) && !PreSimpleTerm.FunctionType.NEGATIVE.equals(group.get(0).getFunctionType())) {
            group = remove(group, group.get(0));
        }

        if (group.size() > 1) {

            PreSimpleTerm firstTerm = group.get(0);
            PreSimpleTerm lastTerm = group.get(group.size() - 1);

            //sort by primary function instead
            if (Times.class.equals(function)) {

            }
            if (PreSimpleTerm.FunctionType.MINUS.equals(firstTerm.getFunctionType())) {
                isNegative = true;
                group = remove(group, firstTerm);

            } else if (PreSimpleTerm.FunctionType.NEGATIVE.equals(firstTerm.getFunctionType())) {
                // this should account for the case of 5 = -10
                if (Function.getOrderOfOperation(function) > Function.getOrderOfOperation(Plus.class)) {
                    isNegative = true;
                    group = remove(group, firstTerm);

                }
            } else if (PreSimpleTerm.FunctionType.DIVIDE.equals(firstTerm.getFunctionType())) {
                isInverse = true;
                group = remove(group, firstTerm);

            } else if (PreSimpleTerm.Type.FUNCTION.equals(firstTerm.getType()) && firstTerm.getFunctionType().getFunction() == AdvancedFunction.class) {
                group = remove(group, firstTerm);
            }

            if (isInParentheses(group)) {
                hasParentheses = true;
                group = removeParentheses(group);
            }

        }

        return new PreSimpleTermGrouping(group, isNegative, isInverse, hasParentheses);


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

    private static boolean isInParentheses(List<PreSimpleTerm> preSimpleTerms) {
        Map<Integer, Integer> parentheses = ParenthesisUtil.getParenthesisGroups(preSimpleTerms);
        try {
            if (parentheses.get(0) == preSimpleTerms.size() - 1) {
                return true;
            }
        } catch (Exception ignore) {
        }
        return false;
    }

    private static List<PreSimpleTerm> removeParentheses(List<PreSimpleTerm> group) {
        List<PreSimpleTerm> newGroup = new ArrayList<PreSimpleTerm>();
        if (isInParentheses(group)) {
            for (int i = 1; i < group.size() - 1; i++) {
                newGroup.add(group.get(i));
            }
        }
        return newGroup;
    }

    private static List<PreSimpleTerm> remove(List<PreSimpleTerm> group, PreSimpleTerm preSimpleTerm) {
        List<PreSimpleTerm> newGroup = new ArrayList<PreSimpleTerm>();
        for (PreSimpleTerm term : group) {
            if (!term.equals(preSimpleTerm)) {
                newGroup.add(term);
            }
        }
        return newGroup;
    }
}
