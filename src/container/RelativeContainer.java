package container;

import tree.Term;
//import android.util.Log;
import container.walks.allignbls;
import container.walks.resize;
import display.rectangle;

public class RelativeContainer {

	static final String TAG = "RelativeContainer";
	public rectangle container;

	
	
	
	
	public Term drawelement(Term ter){

		specificimg current = new specificimg();

		container = current.specificimage(ter);
		ter.container = container;
		resize rs = new resize(ter);
		//fontize fs = new fontize(ter, fontsize);
		//invert it = new invert(ter);
		
		allignbls al = new allignbls(ter);
		
		return ter;
	}

}
