package tree.functions.calculate;

import java.util.ArrayList;

import move.identify.TermMath;

import tree.Term;
import tree.functions.Plus;
import tree.simple.Number;

public class PlusMinus {



	public Term Add(Term tr){
		Term second = likenumbers(tr);

		return second;
	}

	public Term likenumbers(Term tr){
		ArrayList<Term> nums =  new ArrayList<Term>();
		Term second = new Term();
		try {
			 second = (Term)tr.clone();
		} catch (CloneNotSupportedException e) {}
		
		for (int i = 0; i<tr.getChildren().size(); i++){
			Term kid = tr.getChildren().get(i);

			if(kid instanceof Number){
				nums.add(kid);
			}
			else{
				if(kid.isNegative() && kid.getChildren().size()==3 && (kid.getChildren().get(2) instanceof Number)){
					nums.add(kid);
				}
			}
		}

		if(nums.size()>1){
			Term one = nums.get(0);
			Term two = nums.get(1);
			
			 ArrayList<Integer> key = TermMath.findTreePositionOfSelected(tr, one);
			 one = TermMath.findTermUsingKey(second, key);
			 
			 key = TermMath.findTreePositionOfSelected(tr, two);
			 two = TermMath.findTermUsingKey(second, key);
			
			
			
			boolean onepositive = true;
			boolean twopositive = true;
			
			if(one.isNegative()){onepositive = false;}
			
			int twopos = two.getParent().getChildren().indexOf(two);
		/*	if(two.getParent().getChildren().get(twopos-1) instanceof Minus||two.isNegative()){
				twopositive = false;
			}
			if(two.getParent().getChildren().get(twopos-1) instanceof Minus&&two.isNegative()){
				twopositive = true;
			}*/
			
			double onenum;
			double twonum; 
			
			if(one instanceof Number){ onenum = ((Number) one).getValue(); }
			else{onenum =((Number) one.getChildren().get(2)).getValue();}
			if(!onepositive){onenum*=(-1);}
			
			twonum = ((Number)two).getValue();
			if(!twopositive){twonum*=(-1);}
			
			double sum = onenum + twonum;
			
			Term mid;
			if(sum>0){
				 mid = new Number(sum);
			}
			else{
				 mid = new Number(-sum);
				mid.toggleNegative();
			}
			mid.setParent(one.getParent());
			
			two.getParent().getChildren().remove(twopos-1);
			two.getParent().getChildren().remove(twopos-1);
			
			int onepos = one.getParent().getChildren().indexOf(one);
			if(onepos ==0){
				one.getParent().getChildren().set(0, mid);
			}
			else{
				if(!mid.isNegative()){
					Plus pl = new Plus();
					pl.setParent(one.getParent());
					one.getParent().getChildren().set(onepos-1, pl);
					one.getParent().getChildren().set(onepos, mid);
				}
				if(mid.isNegative()){
			/*		Minus mn = new Minus();
					mn.setParent(one.getParent());
					mid.toggleNegative();
					one.getParent().getChildren().set(onepos-1, mn);
					one.getParent().getChildren().set(onepos, mid);*/
				}
			}
			
		}
		
		
		
		return second;
	}

}
