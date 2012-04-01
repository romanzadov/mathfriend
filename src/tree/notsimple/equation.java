package tree.notsimple;

import tree.Term;
import tree.operators.Equals;
import tree.operators.Operator;

public class Equation extends NotSimple{

	Term left;
	Term right;

	public Equation(Term tr){

		boolean okay = true;

		try {
			if(tr.getChildren().size()!=3){okay = false;}
			if(!(tr.operator instanceof Equals)){okay = false;}
			if(tr.getChildren().get(1) instanceof Equals){okay = false;}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			System.out.println("term is not an equals");
			e.printStackTrace();
		}

		if(okay){
			left = tr.getChildren().get(0);
			right = tr.getChildren().get(2);
		}

	}

	//One of the properties of an equation is that if we apply an arbitrary
	//function to both sides of the equation, equality is preserved.

	public Equation Operation(Equation eq, Operator op, Term sel){

		eq = op.ToBothSides(eq, op, sel);

		return eq;
	}

	public boolean isNegative(Term tr) {

		boolean okay = true;

		try {
			if(tr.getChildren().size()!=3){okay = false;}
			if(!(tr.operator instanceof Equals)){okay = false;}
			if(tr.getChildren().get(1) instanceof Equals){okay = false;}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			System.out.println("term is not an equals");
			e.printStackTrace();
		}


		return okay;
	}

    @Override
    public boolean canConstruct(Term tr) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
