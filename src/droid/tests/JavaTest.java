package droid.tests;

import display.point;
import representTerms.Images;
import tree.Term;

public class JavaTest {

	static final String TAG = "JavaTest";
	 
	String main = "2(x-3)+5";
	String sel = "x-3";
	
	public void printTerms(){
		Term mainTerm = new Term(main);
		Term selTerm = new Term(sel);
		
		Images mainImage = new Images(mainTerm, new point(0,0), null);
			
	}
}
