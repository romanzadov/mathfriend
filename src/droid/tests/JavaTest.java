package droid.tests;

import display.point;
import parse.path;
import representTerms.image;
import tree.term;
import android.util.Log;

public class JavaTest {

	static final String TAG = "JavaTest";
	 
	String main = "2(x-3)+5";
	String sel = "x-3";
	
	public void printTerms(){
		path pa = new path();
		term mainTerm = pa.getTermFromString(main);
		pa = new path();
		term selTerm = pa.getTermFromString(sel);
		
		image mainImage = new image(mainTerm, new point(0,0), null);
			
	}
}