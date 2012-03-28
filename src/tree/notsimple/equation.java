package tree.notsimple;

import tree.Term;
import tree.operators.Equals;
import tree.operators.Operator;

public class equation extends NotSimple{

	Term left;
	Term right;

	public equation(Term tr){

		boolean okay = true;

		try {
			if(tr.getChilds().size()!=3){okay = false;}
			if(!(tr.operator instanceof Equals)){okay = false;}
			if(tr.getChilds().get(1) instanceof Equals){okay = false;}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			System.out.println("term is not an equals");
			e.printStackTrace();
		}

		if(okay){
			left = tr.getChilds().get(0);
			right = tr.getChilds().get(2);
		}

	}

	//One of the properties of an equation is that if we apply an arbitrary
	//function to both sides of the equation, equality is preserved.

	public equation Operation(equation eq, Operator op, Term sel){

		eq = op.ToBothSides(eq, op, sel);

		return eq;
	}

	@Override
	public boolean isNegative(Term tr) {

		boolean okay = true;

		try {
			if(tr.getChilds().size()!=3){okay = false;}
			if(!(tr.operator instanceof Equals)){okay = false;}
			if(tr.getChilds().get(1) instanceof Equals){okay = false;}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			System.out.println("term is not an equals");
			e.printStackTrace();
		}


		return okay;
	}

}
