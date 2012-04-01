package parse;

import java.util.ArrayList;

import container.walks.TestPaternity;

import tree.*;
import tree.operators.*;
import tree.simple.SimpleTerm;

public class ParseUtil {


    public PreSimple preSimple = new PreSimple();    //to simple terms
    public PreOrganized preorg = new PreOrganized();
    public TreeGen tr = new TreeGen();

    public Term getTermFromString(String st) {


        ArrayList<Character> characters = ParseCharacterUtil.getCharacterArrayFromString(st);

        ArrayList<SimpleTerm> simp = tosimpleterms(characters);


        Term firstterm;
        if (simp.size() == 1) {
            firstterm = simp.get(0);
        } else if (simp.size() == 3 && simp.get(0) instanceof Parens && simp.get(2) instanceof Parens) {
            firstterm = simp.get(1);
        } else {
            firstterm = toTerms(simp);
        }

        GroupFractions GF = new GroupFractions(firstterm);
        CastToNonSimple CT = new CastToNonSimple(firstterm);


        TestPaternity tp = new TestPaternity(firstterm);
        return firstterm;
    }

    public ArrayList<SimpleTerm> tosimpleterms(ArrayList<Character> formula) {
        ArrayList<SimpleTerm> asimp = preSimple.simplify(formula);
        ArrayList<SimpleTerm> simp = preorg.simplify(asimp);
        return simp;
    }

    public Term toTerms(ArrayList<SimpleTerm> simp) {
        Term firstterm = tr.generatetree(simp);
//		Log.d(TAG, ""+firstterm.getChilds().get(0).toString()+firstterm.getChilds().get(0).hasparen);
        testdraw(firstterm);
        return firstterm;

    }

    public void testdraw(Term tr) {
        for (int i = 0; i < tr.getChildren().size(); i++) {
            if (tr.getChildren().get(i).isSimple()) {
                for (int j = 0; j < tr.getChildren().get(i).simples.size(); j++) {
                    //	System.out.println(tr.childs.get(i).simples.get(j).valuestring);
                    if (i == tr.getChildren().size() - 1) {
                        System.out.println("----------");
                    }
                }
            } else {
                testdraw(tr.getChildren().get(i));
            }

        }
    }


}
