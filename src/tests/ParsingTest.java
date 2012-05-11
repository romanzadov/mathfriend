package tests;


import junit.framework.Assert;
import org.junit.Test;
import tree.Term;

public class ParsingTest {

    @Test
    public void testParseTerm() {

        for(String formula: TestStrings.getStrings()) {

            Term term = new Term(formula);
            System.out.println(formula+ " ==> "+term);
            Assert.assertNotNull(term);

        }

    }

}
