package tests;


import junit.framework.Assert;
import org.junit.Test;
import tree.Term;

public class ParsingTest {

    private static final String[] TEST_STRINGS = {"5=x", "2x", "2+5"};

    @Test
    public void testParseTerm() {

        for(String formula: TEST_STRINGS) {

            Term term = new Term(formula);
            System.out.println("Term: "+term);
            Assert.assertNotNull(term);

     /*       String fromTerm = term.toString();
            Term fromPrintout = new Term(fromTerm);

            Assert.assertEquals(fromTerm, fromPrintout.toString());

            System.out.println(formula + " | " + fromTerm + " | " + fromPrintout.toString());*/

        }


    }
    
}
