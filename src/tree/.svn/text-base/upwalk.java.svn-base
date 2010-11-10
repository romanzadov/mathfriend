package tree;


public class upwalk {
	//walk through all the nodes in up order and change things
	//using performAction
	public interface TreeFunction{
		public void performAction(term tr);
	}
	public upwalk(term tr, TreeFunction f){
		//input the first term
		//for each child, if simple child then execute, if not, dig in
	
		
		if(tr.issimple){
			f.performAction(tr);
		}
		else{
			for(int i = 0; i<tr.getChilds().size(); i++){
				upwalk tree = new upwalk(tr.getChilds().get(i),f);
			}
		}
	
	}
	
}
