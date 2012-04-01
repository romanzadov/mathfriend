package parse;
import java.util.ArrayList;

import container.walks.TestPaternity;

//import android.util.Log;

import tree.*;
import tree.operators.*;
import tree.simple.SimpleTerm;

public class path {

	static final String TAG = "path";
	
	public formula fr = new formula();		//methods for tochar
	public presimple presimp = new presimple();	//to simple terms
	public preorganized preorg = new preorganized();
	public treegen tr = new treegen();

	public Term getTermFromString(String st)
	{
		Term firstterm;
		ArrayList<Character> formula =tochars(st);
		boolean good = grammarcheck(formula);
		if(good){
			ArrayList<SimpleTerm> simp = tosimpleterms(formula);
			
			if(simp.size() == 1){firstterm = simp.get(0);}
			else if(simp.size() == 3 && simp.get(0) instanceof Parens && simp.get(2) instanceof Parens){
				firstterm = simp.get(1);
			}
		
			
			else{firstterm = toTerms(simp);}
			
			GroupFractions GF = new GroupFractions(firstterm);
			CastToNonSimple CT = new CastToNonSimple(firstterm);
			
		}
		else{
			firstterm = new Times();
		}
		TestPaternity tp = new TestPaternity(firstterm);
		return firstterm;
	}

	public boolean grammarcheck(ArrayList<Character> formula){
		GrammarCheck gc = new GrammarCheck();
		boolean good = gc.CheckChars(formula);
		return good;
	}

	public ArrayList<Character> tochars(String st)
	{
		ArrayList<Character> formula = fr.toarray(st);
		formula = fr.removespaces(formula);
		return formula;
	}

	public ArrayList<SimpleTerm> tosimpleterms(ArrayList<Character> formula)
	{
		ArrayList<SimpleTerm> asimp = presimp.simplify(formula);
		ArrayList<SimpleTerm> simp = preorg.simplify(asimp);
		return simp;
	}

	public Term toTerms(ArrayList<SimpleTerm> simp)
	{
		Term firstterm = tr.generatetree(simp);
//		Log.d(TAG, ""+firstterm.getChilds().get(0).toString()+firstterm.getChilds().get(0).hasparen);
		testdraw(firstterm);
		return firstterm;

	}

	public void testdraw(Term tr){
		for(int i = 0; i<tr.getChildren().size();i++){
			if(tr.getChildren().get(i).issimple){
				for(int j = 0; j<tr.getChildren().get(i).simples.size();j++){
					//	System.out.println(tr.childs.get(i).simples.get(j).valuestring);
					if(i == tr.getChildren().size()-1){
						System.out.println("----------");
					}
				}
			}
			else{testdraw(tr.getChildren().get(i));}

		}
	}


}
