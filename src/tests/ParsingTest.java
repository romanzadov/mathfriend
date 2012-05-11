package tests;


import junit.framework.Assert;
import org.junit.Test;
import tree.CompoundTerm;

public class ParsingTest {

    @Test
    public void testParseTerm() {

        for(String formula: TestStrings.getStrings()) {

            CompoundTerm term = new CompoundTerm(formula);
            System.out.println(formula+ " ==> "+term);
            Assert.assertNotNull(term);

        }

    }

}
