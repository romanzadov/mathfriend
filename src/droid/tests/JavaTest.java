package droid.tests;

import display.point;
import parse.path;
import representTerms.Image;
import tree.Term;

public class JavaTest {

	static final String TAG = "JavaTest";
	 
	String main = "2(x-3)+5";
	String sel = "x-3";
	
	public void printTerms(){
		path pa = new path();
		Term mainTerm = pa.getTermFromString(main);
		pa = new path();
		Term selTerm = pa.getTermFromString(sel);
		
		Image mainImage = new Image(mainTerm, new point(0,0), null);
			
	}
}
