package tree;

import tree.upwalk.TreeFunction;


public class downwalk {
	//walk through all the nodes in down order and change things
	//using performAction
	public interface TreeFunction{
		public void performAction(term tr);
		
	}
	public downwalk(term tr, TreeFunction f){
		//input the first term
		//for each child, execute.
		//if not simple, dig in
		
		f.performAction(tr);
		
		if(tr != null && !tr.issimple){
			for(int i = 0; i<tr.getChilds().size(); i++){
			downwalk tree = new downwalk(tr.getChilds().get(i),f);
			}
		}
	}
	
}
