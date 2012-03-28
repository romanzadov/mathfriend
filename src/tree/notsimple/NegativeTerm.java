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
		Negative n = new Negative();
		this.operator = n;
		n = new Negative();
		
		this.setNegative(true);
		this.getChilds().add(n);
		this.getChilds().add(tr);
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

	@Override
	public boolean canConstruct(Term tr) {
		boolean ans = false;
		if(tr.isNegative() && tr.getChilds().size() == 3){
			ans = true;
		}

		return ans;
	}
}
