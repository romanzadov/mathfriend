package tests;


import junit.framework.Assert;
import org.junit.Test;
import tree.Term;
import tree.compound.CompoundTerm;

public class ParsingTest {

    @Test
    public void testParseTerm() {

        for(String formula: TestStrings.getStrings()) {

            Term term = Term.getTermFromString(formula);
            System.out.println(formula+ " ==> "+term);
            Assert.assertNotNull(term);

        }

    }

}
