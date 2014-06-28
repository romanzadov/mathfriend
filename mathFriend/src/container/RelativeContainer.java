package container;

import tree.compound.CompoundTerm;
//import android.util.Log;
import container.walks.allignbls;
import container.walks.resize;
import display.rectangle;

public class RelativeContainer {

	static final String TAG = "RelativeContainer";
	public rectangle container;

	
	
	
	
	public CompoundTerm drawelement(CompoundTerm ter){

		specificimg current = new specificimg();

		container = current.specificimage(ter);
		ter.setContainer(container);
		resize rs = new resize(ter);
		//fontize fs = new fontize(ter, fontsize);
		//invert it = new invert(ter);
		
		allignbls al = new allignbls(ter);
		
		return ter;
	}

}
