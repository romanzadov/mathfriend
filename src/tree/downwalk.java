package tree;


public class downwalk {
	//walk through all the nodes in down order and change things
	//using performAction
	public interface TreeFunction{
		public void performAction(Term tr);
		
	}
	public downwalk(Term tr, TreeFunction f){
		//input the first term
		//for each child, execute.
		//if not simple, dig in
		
		f.performAction(tr);
		
		if(tr != null && !tr.issimple){
			for(int i = 0; i<tr.getChildren().size(); i++){
			downwalk tree = new downwalk(tr.getChildren().get(i),f);
			}
		}
	}
	
}