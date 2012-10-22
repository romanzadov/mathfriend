package com.mathfriend.shared;

public class FieldVerifier {

	final static String[] invalidStrings = {"{", "}", "[", "]", "&", "!", "@", "\'", "\"", "#", "%"};
	
	public static boolean isValidFormula(String formula) {
		if (formula == null) {
			return false;
		}
		boolean hasNoInvalidCharacters = true;
		for (String character: invalidStrings) {
			if(formula.contains(character)) {
				hasNoInvalidCharacters = false;
			}
		}
		return hasNoInvalidCharacters;
	}
}
