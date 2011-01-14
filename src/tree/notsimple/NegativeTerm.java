package tree.notsimple;

import parse.path;
import tree.Term;
import tree.operators.negative;
import tree.simple.Number;
import display.rectangle;

public class NegativeTerm extends NotSimple{

	public NegativeTerm(){}
	
	public NegativeTerm(Term tr){
		if(isNegative(tr)){
			negative n = new negative();
			this.operator = n;
			n = new negative();

	//		Number minusone = new Number(-1);

			this.setNegative(true);
			this.hasparen = tr.hasparen;
	//		this.childs.add(minusone);
			this.childs.add(n);
			this.childs.add(tr.getChilds().get(2));
			for(int i = 0; i<this.getChilds().size(); i++){
				this.getChilds().get(i).parent = this;
			}
		}
		
		else{
			makeNegative(tr);
		
		}
	}

	public void makeNegative(Term tr){
		negative n = new negative();
		this.operator = n;
		n = new negative();
		
		this.setNegative(true);
		this.getChilds().add(n);
		this.getChilds().add(tr);
	}

	public boolean isNegative(Term tr){
		boolean ans = false;
		if(tr.isNegative() && tr.getChilds().size() == 3){
			ans = true;
		}

		return ans;
	}

	@Override
	public String toString(){
		String st = "(-";
		if(this.getChilds().size()==3){
			Term tr = this.getChilds().get(2);
			st +=tr.toString();
		}
		else if(this.getChilds().size()==2){
			Term tr = this.getChilds().get(1);
			st +=tr.toString();
		}
		st+=")";
		return st;

	}

	public rectangle giverect(Term tr){

		rectangle a = new rectangle();
		float xmax= 0;
		float ymax = 0;
		for(int i = 0; i<tr.getChilds().size(); i++){
			rectangle b = tr.getChilds().get(i).container;
			if(b.width>xmax){xmax = b.width;}
			ymax += b.height;
		}
		a.height = ymax;
		a.width = xmax;
		tr.container = a;
		return a;
	}
}
