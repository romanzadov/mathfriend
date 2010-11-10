package tree.operators.calculate;

import java.util.ArrayList;

import move.identify.FindSel;
import move.identify.ReturnSel;

import tree.term;
import tree.operators.minus;
import tree.operators.plus;
import tree.operators.times;
import tree.simple.Number;

public class PlusMinus {



	public term Add(term tr){
		term second = likenumbers(tr);

		return second;
	}

	public term likenumbers(term tr){
		ArrayList<term> nums =  new ArrayList<term>();
		term second = new term();
		try {
			 second = (term)tr.clone();
		} catch (CloneNotSupportedException e) {}
		
		for (int i = 0; i<tr.getChilds().size(); i++){
			term kid = tr.getChilds().get(i);

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
			term one = nums.get(0);
			term two = nums.get(1);
			
			FindSel fs = new FindSel();
			 ArrayList<Integer> key = fs.FindSelected(tr, one);
			 ReturnSel rs = new ReturnSel();
			 one = rs.Return(second, key);
			 
			 key = fs.FindSelected(tr, two);
			 two = rs.Return(second, key);
			
			
			
			boolean onepositive = true;
			boolean twopositive = true;
			
			if(one.isNegative()){onepositive = false;}
			
			int twopos = two.parent.getChilds().indexOf(two);
			if(two.parent.getChilds().get(twopos-1) instanceof minus||two.isNegative()){
				twopositive = false;
			}
			if(two.parent.getChilds().get(twopos-1) instanceof minus&&two.isNegative()){
				twopositive = true;
			}
			
			double onenum;
			double twonum; 
			
			if(one instanceof Number){ onenum = ((Number) one).getvalue(); }
			else{onenum =((Number) one.getChilds().get(2)).getvalue();}
			if(!onepositive){onenum*=(-1);}
			
			twonum = ((Number)two).getvalue();
			if(!twopositive){twonum*=(-1);}
			
			double sum = onenum + twonum;
			
			term mid;
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
					plus pl = new plus();
					pl.parent = one.parent;
					one.parent.getChilds().set(onepos-1, pl);
					one.parent.getChilds().set(onepos, mid);
				}
				if(mid.isNegative()){
					minus mn = new minus();
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
