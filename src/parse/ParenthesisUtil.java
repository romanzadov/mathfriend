package parse;

import java.util.ArrayList;

public class ParenthesisUtil {


    public static ArrayList<int[]> getParenthesisGroups(ArrayList<PreSimpleTerm> formula) {

        int[] placesOfParenthesisInFormula = new int[formula.size()];

        checkFormula(formula);

        for (int i = 0; i < formula.size(); i++) {
            if (formula.get(i).getCharacters().equals('(')) {
                placesOfParenthesisInFormula[i] = 1;  //left parentheses
            }
            if (formula.get(i).getCharacters().equals(')')) {
                placesOfParenthesisInFormula[i] = 2; //right parenthes
            }
        }

        int n = 0;
        int formulaLength = formula.size();
        ArrayList<int[]> parenthesesPairs = new ArrayList<int[]>();
        boolean done = false;

        while (done == false) {
            for (int i = 0; i < formulaLength; i++) {

                int leftParenthesisPosition;
                int rightParenthesisPosition;

                if (placesOfParenthesisInFormula[i] == 1) {
                    leftParenthesisPosition = i;
                    for (int j = i; j < formulaLength; j++) {
                        if (placesOfParenthesisInFormula[j] == 0) {
                        }
                        if (placesOfParenthesisInFormula[j] == 1) {
                            leftParenthesisPosition = j;
                        }
                        if (placesOfParenthesisInFormula[j] == 2) {
                            rightParenthesisPosition = j;
                            int[] ends = {leftParenthesisPosition, rightParenthesisPosition};
                            parenthesesPairs.add(n, ends);
                            placesOfParenthesisInFormula[rightParenthesisPosition] = 0;
                            placesOfParenthesisInFormula[leftParenthesisPosition] = 0;
                            n++;
                            leftParenthesisPosition = 0;
                            rightParenthesisPosition = 0;
                            j = formulaLength;
                        }


                    }
                }
                int sum = 0;
                for (int k = 0; k < formulaLength; k++) {
                    sum += placesOfParenthesisInFormula[k];
                }
                if (sum == 0) {
                    done = true;
                }

            }
        }
        return parenthesesPairs;

    }

    private static void checkFormula(ArrayList<PreSimpleTerm> formula) {

        ArrayList<Integer> leftParenthesisPositions = new ArrayList<Integer>();
        ArrayList<Integer> rightParenthesisPositions = new ArrayList<Integer>();

        for(PreSimpleTerm preSimpleTerm: formula) {
            if(preSimpleTerm.getType().equals(PreSimpleTerm.Type.PARENTHESES)) {
                if(preSimpleTerm.getCharacters().get(0).equals('(')) {
                    leftParenthesisPositions.add(formula.indexOf(preSimpleTerm));
                } else {
                    rightParenthesisPositions.add(formula.indexOf(preSimpleTerm));
                }
            }
        }

        if(leftParenthesisPositions.size() != rightParenthesisPositions.size()) {
            throw new RuntimeException("Parentheses do not match. There are "+leftParenthesisPositions.size()+" left parentheses and "+ rightParenthesisPositions.size() + " right parentheses.");
        }

        for(Integer leftPosition : leftParenthesisPositions) {
            int rightPosition = rightParenthesisPositions.get(leftParenthesisPositions.indexOf(leftPosition));
            if(rightPosition < leftPosition) {
                throw new RuntimeException("Parentheses not matchable. Right one is before left one.");
            }
        }


    }
}
