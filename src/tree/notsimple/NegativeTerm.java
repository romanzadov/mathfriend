package tree.notsimple;

import tree.Term;
import tree.operators.Negative;
import display.rectangle;

public class NegativeTerm extends NotSimple{

	public NegativeTerm(){}
	
	public NegativeTerm(Term tr){
		if(canConstruct(tr)){
			Negative n = new Negative();
			this.operator = n;
			n = new Negative();

	//		Number minusone = new Number(-1);

			this.setNegative(true);
			this.hasParentheses = tr.hasParentheses;
	//		this.childs.add(minusone);
			this.children.add(n);
			this.children.add(tr.getChildren().get(2));
			for(int i = 0; i<this.getChildren().size(); i++){
				this.getChildren().get(i).parent = this;
			}
		}
		
		else{
			makeNegative(tr);
		
		}
	}

	public void makeNegative(Term tr){
		Negative n = new Negative();
		this.operator = n;
		n = new Negative();
		
		this.setNegative(true);
		this.getChildren().add(n);
		this.getChildren().add(tr);
	}


	@Override
	public String toString(){
		String st = "(-";
		if(this.getChildren().size()==3){
			Term tr = this.getChildren().get(2);
			st +=tr.toString();
		}
		else if(this.getChildren().size()==2){
			Term tr = this.getChildren().get(1);
			st +=tr.toString();
		}
		st+=")";
		return st;

	}

	public rectangle giverect(Term tr){

		rectangle a = new rectangle();
		float xmax= 0;
		float ymax = 0;
		for(int i = 0; i<tr.getChildren().size(); i++){
			rectangle b = tr.getChildren().get(i).container;
			if(b.width>xmax){xmax = b.width;}
			ymax += b.height;
		}
		a.height = ymax;
		a.width = xmax;
		tr.container = a;
		return a;
	}

	@Override
	public boolean canConstruct(Term tr) {
		boolean ans = false;
		if(tr.isNegative() && tr.getChildren().size() == 3){
			ans = true;
		}

		return ans;
	}
}