package tests;


import junit.framework.Assert;
import org.junit.Test;
import tree.Term;

public class ParsingTest {

    @Test
    public void testParseTerm() {

        for(String formula: TestStrings.getStrings()) {

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
