package tree.operators.calculate;

import java.util.ArrayList;

import move.identify.TermMath;
import move.identify.ReturnSel;

import tree.Term;
import tree.operators.Minus;
import tree.operators.Plus;
import tree.operators.Times;
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
		
		for (int i = 0; i<tr.getChilds().size(); i++){
			Term kid = tr.getChilds().get(i);

			if(kid instanceof Number){
				nums.add(kid);
			}
			else{
				if(kid.isNegative() && kid.getChilds().size()==3 && (kid.getChilds().get(2) instanceof Number)){
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
			
			int twopos = two.parent.getChilds().indexOf(two);
			if(two.parent.getChilds().get(twopos-1) instanceof Minus||two.isNegative()){
				twopositive = false;
			}
			if(two.parent.getChilds().get(twopos-1) instanceof Minus&&two.isNegative()){
				twopositive = true;
			}
			
			double onenum;
			double twonum; 
			
			if(one instanceof Number){ onenum = ((Number) one).getValue(); }
			else{onenum =((Number) one.getChilds().get(2)).getValue();}
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
			mid.parent = one.parent;
			
			two.parent.getChilds().remove(twopos-1);
			two.parent.getChilds().remove(twopos-1);
			
			int onepos = one.parent.getChilds().indexOf(one);
			if(onepos ==0){
				one.parent.getChilds().set(0, mid);
			}
			else{
				if(!mid.isNegative()){
					Plus pl = new Plus();
					pl.parent = one.parent;
					one.parent.getChilds().set(onepos-1, pl);
					one.parent.getChilds().set(onepos, mid);
				}
				if(mid.isNegative()){
					Minus mn = new Minus();
					mn.parent = one.parent;
					mid.toggleNegative();
					one.parent.getChilds().set(onepos-1, mn);
					one.parent.getChilds().set(onepos, mid);
				}
			}
			
		}
		
		
		
		return second;
	}

}
