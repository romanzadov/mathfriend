package tree.operators;

import tree.Term;
import tree.simple.simpleterm;
import display.rectangle;

public class parens extends simpleterm{

	public Character value;
	
	@Override
	public String toString(){
		return ""+value;
	}

	public void setvalue(Character v){
		value = v;
		if(value.equals('(')){
			rmult = false;
			lmult = true;
		}

		if(value.equals(')')){
			rmult = true;
			lmult = false;
		}
	}


	public boolean isoperator(){
		boolean a = false;
		return a;
	}



	public rectangle giverect(Term tr){
		

		
		if(tr instanceof parens){
			
		}
		
		if(tr.container == null){
			System.out.println("error in parens: not containered");
		}
		
		else if(tr.getChilds().size()>0){
			if((tr.getChilds().get(0) instanceof parens)&&
					(tr.getChilds().get(tr.getChilds().size()-1) instanceof parens)){

			}

			else{
				parens left = new parens();
				rectangle rect = new rectangle();
				left.container = rect;
				left.container.height = tr.container.height;
				left.container.width = tr.container.height/2;
				left.todraw = "(";
				left.issimple = true;
				left.simples.add(left);
				left.parent = tr;
				left.value = '(';
				tr.getChilds().add(0,left);

				for(int i = 1; i< tr.getChilds().size(); i++){
					tr.getChilds().get(i).container.bl.x += left.container.width;	
				}

				parens right = new parens();
				rectangle rect2 = new rectangle();
				right.container = rect2;
				right.container.height = tr.container.height;
				right.container.width = tr.container.height/2;
				right.todraw = ")";
				right.value = ')';
				right.issimple = true;
				right.container.bl.x = left.container.width + tr.container.width;
				right.simples.add(right);
				right.parent = tr;
				tr.getChilds().add(right);

				tr.container.width +=2*left.container.width;
			}
		}
		
		return tr.container;
	}
	

	public boolean equals(Object a)
	{
		boolean ans = false;
		if(a.equals(value)){ans=true;}
		return ans;
	}

}
