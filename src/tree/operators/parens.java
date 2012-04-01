package tree.operators;

import tree.Term;
import tree.simple.SimpleTerm;
import display.rectangle;

public class Parens extends SimpleTerm {

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
		

		
		if(tr instanceof Parens){
			
		}
		
		if(tr.container == null){
			System.out.println("error in parens: not containered");
		}
		
		else if(tr.getChildren().size()>0){
			if((tr.getChildren().get(0) instanceof Parens)&&
					(tr.getChildren().get(tr.getChildren().size()-1) instanceof Parens)){

			}

			else{
				Parens left = new Parens();
				rectangle rect = new rectangle();
				left.container = rect;
				left.container.height = tr.container.height;
				left.container.width = tr.container.height/2;
				left.toDraw = "(";
				left.issimple = true;
				left.simples.add(left);
				left.parent = tr;
				left.value = '(';
				tr.getChildren().add(0,left);

				for(int i = 1; i< tr.getChildren().size(); i++){
					tr.getChildren().get(i).container.bl.x += left.container.width;
				}

				Parens right = new Parens();
				rectangle rect2 = new rectangle();
				right.container = rect2;
				right.container.height = tr.container.height;
				right.container.width = tr.container.height/2;
				right.toDraw = ")";
				right.value = ')';
				right.issimple = true;
				right.container.bl.x = left.container.width + tr.container.width;
				right.simples.add(right);
				right.parent = tr;
				tr.getChildren().add(right);

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
