package tests;


import junit.framework.Assert;
import org.junit.Test;
import parse.ParseCharacterUtil;
import tree.Term;
import tree.compound.CompoundTerm;
import tree.functions.Equals;
import tree.functions.Plus;
import tree.functions.Times;

import java.util.ArrayList;
import java.util.List;

public class ParsingTest {

    @Test
    public void testParseTerm() {

        List<String> results = TestStrings.getResults();

        for(String formula: TestStrings.getStrings()) {

            Term term = Term.getTermFromString(formula);
            System.out.println(formula+ " ==> "+term);
            Assert.assertNotNull(term);

            String result = results.get(TestStrings.getStrings().indexOf(formula));
            Assert.assertEquals(result, term.toString());

        }

    }
   /* @Test
    public void testParseTerm$() {

        for(String formula: TestStrings.getStrings()) {
            if (formula.contains("(")) continue;

            List<Character> chars = ParseCharacterUtil.getCharacterArrayFromString(formula);

            List<Term> terms = new ArrayList<Term>();
            StringBuffer buffer = new StringBuffer();
            for (char c : chars) {
                if (c == '=') {
                    terms.add(parseAddition(buffer.toString()));
                    buffer = new StringBuffer();
                } else {
                    buffer.append(c);
                }
            }
            terms.add(parseAddition(buffer.toString()));



            CompoundTerm term = new CompoundTerm(Equals.class);
            term.getChildren().addAll(terms);
            System.out.println(formula+ " ==> "+term);
            Assert.assertNotNull(term);

        }
    }
*/
    public Term parseAddition(String termString) {
        List<Term> terms = new ArrayList<Term>();
        StringBuffer buffer = new StringBuffer();
        for (char c : termString.toCharArray()) {
            if ((c == '+' || c == '-') && buffer.length() > 0 && buffer.charAt(buffer.length()-1) != '*' && buffer.charAt(buffer.length()-1) != '/') {
                terms.add(parseMultiplication(buffer.toString()));
                buffer = new StringBuffer();
            }
            buffer.append(c);
        }
        terms.add(parseMultiplication(buffer.toString()));
        CompoundTerm term = new CompoundTerm(Plus.class);
        term.getChildren().addAll(terms);
        return term;
    }

    public Term parseMultiplication(String termString) {
        List<Term> terms = new ArrayList<Term>();
        StringBuffer buffer = new StringBuffer();
        char lastOp = 0;
        for (char c : termString.toCharArray()) {
            if (buffer.length() > 0 && (c == '*' || c == '/' || (!isSign(buffer.charAt(buffer.length()-1)) && isNumeric(c) != isNumeric(buffer.charAt(buffer.length()-1))))) {
                final Term term = Term.getTermFromString(buffer.toString());
                if (lastOp == '/') {
                    term.setInverse(true);
                }
                terms.add(term);
                lastOp = c;
                buffer = new StringBuffer();
                if (c != '*' && c != '/') {
                    buffer.append(c);
                }
            } else {
                buffer.append(c);
            }
        }
        {
            final Term lastTerm = Term.getTermFromString(buffer.toString());
            if (lastOp == '/') {
                lastTerm.setInverse(true);
            }
            terms.add(lastTerm);
        }
        CompoundTerm term = new CompoundTerm(Times.class);
        term.getChildren().addAll(terms);
        return term;
    }

    private boolean isNumeric(char c) {
        for (char numeric : "+-0123456789.".toCharArray()) {
            if (c == numeric) return true;
        }
        return false;
    }

    private boolean isSign(char c) {
        for (char numeric : "+-".toCharArray()) {
            if (c == numeric) return true;
        }
        return false;
    }
}
