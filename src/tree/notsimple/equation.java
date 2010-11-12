package tree.notsimple;

import tree.term;
import tree.operators.equals;
import tree.operators.operator;

public class equation extends NotSimple{

	term left;
	term right;

	public equation(term tr){

		boolean okay = true;

		try {
			if(tr.getChilds().size()!=3){okay = false;}
			if(!(tr.operator instanceof equals )){okay = false;}
			if(tr.getChilds().get(1) instanceof equals){okay = false;}
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

	public equation Operation(equation eq, operator op, term sel){

		eq = op.ToBothSides(eq, op, sel);

		return eq;
	}

	@Override
	public boolean isNegative(term tr) {

		boolean okay = true;

		try {
			if(tr.getChilds().size()!=3){okay = false;}
			if(!(tr.operator instanceof equals )){okay = false;}
			if(tr.getChilds().get(1) instanceof equals){okay = false;}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			System.out.println("term is not an equals");
			e.printStackTrace();
		}


		return okay;
	}

}
