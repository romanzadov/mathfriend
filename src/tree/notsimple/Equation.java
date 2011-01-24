package tree.notsimple;

import java.util.ArrayList;

import tree.Term;
import tree.operators.Equality;
import tree.operators.Operator;

public class Equation extends NotSimple{

	Term left;
	Equality equals = new Equality();
	Term right;

	public Equation(){}

	public Term getLeft(){
		return getChilds().get(0);
	}

	public Term getRight(){
		return getChilds().get(2);
	}

	@Override
	public ArrayList<Term> getChilds(){
		ArrayList<Term> kids = new ArrayList<Term>();
		kids.add(left);
		kids.add(equals);
		kids.add(right);
		return kids;
	}

	public Equation(Term tr){

		if(canConstruct(this)){
			left = tr.getChilds().get(0);
			right = tr.getChilds().get(2);
		}

	}

	public void setChilds(Term left, Term right) {

		this.left = left;
		this.right = right;

		left.parent = this;
		right.parent = this;

	}


	//One of the properties of an equation is that if we apply an arbitrary
	//function to both sides of the equation, equality is preserved.

	public Equation leftOperation(Equation eq, Operator op, Term sel){


		Operator opClone = null;
		Operator secondClone = null;
		Operator thirdClone = null;
		Operator fourthClone = null;
		Term selClone = null;
		Term selSecond = null;
		try {
			opClone = (Operator)op.clone();
			secondClone = (Operator)op.clone();
			thirdClone = (Operator)op.clone();
			fourthClone = (Operator)op.clone();

			selClone = (Term) sel.clone();
			selSecond = (Term)sel.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//do operation on left side
		Term newLeft = new Term();
		newLeft.operator = opClone;

		newLeft.getChilds().add(selClone);
		newLeft.getChilds().add(secondClone);
		newLeft.getChilds().add(eq.getLeft());

		selClone.parent = newLeft;
		secondClone.parent = newLeft;
		eq.getLeft().parent = newLeft;

		//do operation on right side
		Term newRight = new Term();
		newRight.operator = fourthClone;

		newRight.getChilds().add(selSecond);
		newRight.getChilds().add(thirdClone);
		newRight.getChilds().add(eq.getRight());

		selSecond.parent = newRight;
		thirdClone.parent = newRight;
		eq.getRight().parent = newRight;

		//combine both sides into a new equation
		eq.setChilds(newLeft, newRight);

		return eq;
	}

	public Equation rightOperation(Equation eq, Operator op, Term sel){

		Operator opClone = null;
		Operator secondClone = null;
		Operator thirdClone = null;
		Operator fourthClone = null;
		Term selClone = null;
		Term selSecond = null;
		try {
			opClone = (Operator)op.clone();
			secondClone = (Operator)op.clone();
			thirdClone = (Operator)op.clone();
			fourthClone = (Operator)op.clone();

			selClone = (Term) sel.clone();
			selSecond = (Term)sel.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//do operation on left side
		Term newLeft = new Term();
		newLeft.operator = opClone;


		newLeft.getChilds().add(eq.getLeft());
		newLeft.getChilds().add(secondClone);
		newLeft.getChilds().add(selClone);

		selClone.parent = newLeft;
		secondClone.parent = newLeft;
		eq.getLeft().parent = newLeft;

		//do operation on right side
		Term newRight = new Term();
		newRight.operator = fourthClone;

		newRight.getChilds().add(eq.getRight());
		newRight.getChilds().add(thirdClone);
		newRight.getChilds().add(selSecond);

		selSecond.parent = newRight;
		thirdClone.parent = newRight;
		eq.getRight().parent = newRight;

		//combine both sides into a new equation
		eq.setChilds(newLeft, newRight);
		return eq;
	}


	@Override
	public boolean canConstruct(Term tr) {
		boolean okay = false;

		if(tr.getChilds().size() == 3){
			if(tr.getChilds().get(1) instanceof Equality){
				if(tr.operator instanceof Equality){
					okay = true;
				}
			}
		}


		return okay;}

}
