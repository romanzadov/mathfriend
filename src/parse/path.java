package parse;
import java.util.ArrayList;

import container.walks.TestPaternity;

//import android.util.Log;

import parse.*;
import tree.*;
import tree.operators.*;
import tree.simple.simpleterm;

public class path {

	static final String TAG = "path";
	
	public formula fr = new formula();		//methods for tochar
	public presimple presimp = new presimple();	//to simple terms
	public preorganized preorg = new preorganized();
	public treegen tr = new treegen();

	public term getTermFromString(String st)
	{
		term firstterm;
		ArrayList<Character> formula =tochars(st);
		boolean good = grammarcheck(formula);
		if(good){
			ArrayList<simpleterm> simp = tosimpleterms(formula);

			firstterm = toterms(simp);
			GroupFractions GF = new GroupFractions(firstterm);
			CastToNonSimple CT = new CastToNonSimple(firstterm);
			
		}
		else{
			firstterm = new times();
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

	public ArrayList<simpleterm> tosimpleterms(ArrayList<Character> formula)
	{
		ArrayList<simpleterm> asimp = presimp.simplify(formula);
		ArrayList<simpleterm> simp = preorg.simplify(asimp);
		return simp;
	}

	public term toterms(ArrayList<simpleterm> simp)
	{
		term firstterm = tr.generatetree(simp);
//		Log.d(TAG, ""+firstterm.getChilds().get(0).toString()+firstterm.getChilds().get(0).hasparen);
		testdraw(firstterm);
		return firstterm;

	}

	public void testdraw(term tr){
		for(int i = 0; i<tr.getChilds().size();i++){
			if(tr.getChilds().get(i).issimple){
				for(int j = 0; j<tr.getChilds().get(i).simples.size();j++){
					//	System.out.println(tr.childs.get(i).simples.get(j).valuestring);
					if(i == tr.getChilds().size()-1){
						System.out.println("----------");
					}
				}
			}
			else{testdraw(tr.getChilds().get(i));}

		}
	}


}
