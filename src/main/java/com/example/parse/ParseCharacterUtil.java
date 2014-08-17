package com.example.parse;

import java.util.ArrayList;

public class ParseCharacterUtil {

    int equal_location;
    ArrayList<int[]> parenstructure = new ArrayList<int[]>();

    public static ArrayList<Character> getCharacterArrayFromString(String st) {
        ArrayList<Character> formula = new ArrayList<Character>();
        char[] form = st.toCharArray();
        for (int i = 0; i < form.length; i++) {
            formula.add(form[i]);
        }

        if (GrammarCheck.CheckChars(formula)) {
            return removeSpaces(formula);
        } else {
            throw new RuntimeException("Formula did not pass grammar check:  " + st){
				private static final long serialVersionUID = 1L;};
        }

    }

    private static ArrayList<Character> removeSpaces(ArrayList<Character> formula) {

        for (int i = 0; i < formula.size(); i++) {
            if (Character.isSpaceChar(formula.get(i)) == true) {
                formula.remove(i);
            }
        }
        return formula;
    }


    public void equals(ArrayList<Character> formula) {
        int j = 0;

        for (int i = 0; i < formula.size(); i++) {
            if (formula.get(i) == 61) {
                equal_location = i;
                j++;
            }
            if (j == 2) {
                System.out.println("too many equals");
            }

        }


    }


    public void test(ArrayList<Character> formula) {
        for (int i = 0; i < formula.size(); i++) {
            System.out.print(formula.get(i));

        }
        System.out.println("");
        System.out.println("paren  ");
        for (int i = 0; i < parenstructure.size(); i++) {
            System.out.println(parenstructure.get(i)[0] + "  " + parenstructure.get(i)[1]);
        }

    }
}
