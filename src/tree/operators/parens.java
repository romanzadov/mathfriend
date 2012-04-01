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
		
		if(tr.getContainer() == null){
			System.out.println("error in parens: not containered");
		}
		
		else if(tr.getChildren().size()>0){
			if((tr.getChildren().get(0) instanceof Parens)&&
					(tr.getChildren().get(tr.getChildren().size()-1) instanceof Parens)){

			}

			else{
				Parens left = new Parens();
				rectangle rect = new rectangle();
				left.setContainer(rect);
				left.getContainer().height = tr.getContainer().height;
				left.getContainer().width = tr.getContainer().height/2;
				left.setValueString("(");
				left.simples.add(left);
				left.setParent(tr);
				left.value = '(';
				tr.getChildren().add(0,left);

				for(int i = 1; i< tr.getChildren().size(); i++){
					tr.getChildren().get(i).getContainer().bl.x += left.getContainer().width;
				}

				Parens right = new Parens();
				rectangle rect2 = new rectangle();
				right.setContainer(rect2);
				right.getContainer().height = tr.getContainer().height;
				right.getContainer().width = tr.getContainer().height/2;
                right.setValueString(")");
				right.value = ')';
				right.getContainer().bl.x = left.getContainer().width + tr.getContainer().width;
				right.simples.add(right);
				right.setParent(tr);
				tr.getChildren().add(right);

				tr.getContainer().width +=2* left.getContainer().width;
			}
		}
		
		return tr.getContainer();
	}
	

	public boolean equals(Object a)
	{
		boolean ans = false;
		if(a.equals(value)){ans=true;}
		return ans;
	}

}
