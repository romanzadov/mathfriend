package move.operators;

import container.walks.TestPaternity;
import representTerms.Image;
import tree.Term;
import tree.notsimple.Fraction;
import tree.operators.Divide;
import tree.operators.Minus;
import tree.operators.Operator;
import tree.operators.Plus;
import tree.operators.Times;

public class TimesTerm {

	public void Times(Term tr, Term sel){

		if(tr.operator instanceof Times || tr.operator instanceof Divide){
			regularTimes(tr, sel);
		}

		else if(tr.operator instanceof Plus || tr.operator instanceof Minus){
			for(int i = 0; i< tr.getChilds().size(); i++){
				if(!(tr.getChilds().get(i) instanceof Operator)){

					Term resel = null;
					try {
						resel = (Term)sel.clone();
					} catch (CloneNotSupportedException e) {}
					resel.parent = sel.parent;
					regularTimes(tr.getChilds().get(i), resel);
				}
			}
		}
		else{
			regularTimes(tr, sel);
		}


	}

	public void regularTimes(Term tr, Term sel){
		Times tm = new Times();
		if(tr.operator instanceof Times || tr.operator instanceof Divide){
			sel.parent = tr;
			tm.parent = tr;
			tr.getChilds().add(0,tm);
			tr.getChilds().add(0,sel);
		}
		else{
			Term mid = new Term();
			mid.operator = new Times();
			mid.parent = tr.parent;

			int trplace = tr.parent.getChilds().indexOf(tr);

			mid.getChilds().add(sel);
			mid.getChilds().add(tm);
			mid.getChilds().add(tr);

			tr.parent = mid;
			tm.parent = mid;
			sel.parent = mid;

			mid.parent.getChilds().set(trplace, mid);
		}

	}

	public static Term simpleTimes(Term tr){

		//multiply real numbers together
		
		if(tr == null){System.out.println("tred to TimesTerm a null term"); return null;}
		else{

			Term firstMultiple = null;
			Term secondMultiple = null;
			Term resultingTerm = null;
			double firstValue = Double.MIN_VALUE;
			double secondValue = Double.MIN_VALUE;
			boolean done = false;

			
			//first, try to do simple integer multiplication
			for(int i = 0; i<tr.getChilds().size(); i++){
				if(!done){

					Term kid = tr.getChilds().get(i);

					if(kid.isDecimal()){
						
						if(firstMultiple == null){
							firstMultiple = kid;
							firstValue = Term.getNumericValue(kid);
						}
						else{
							secondMultiple = kid;
							secondValue = Term.getNumericValue(kid);

							String val = String.valueOf(firstValue*secondValue);
							Image img = new Image(val,2,2,2);
							Term result = img.tr;

							try {
								resultingTerm = (Term) tr.clone();
							} catch (CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							//remove the second addent and replace the first addent with our result
							resultingTerm = TimesMove.removeChild(resultingTerm, tr.getChilds().indexOf(secondMultiple));

							resultingTerm = TimesMove.replaceMultiplicativeTerm(resultingTerm, result, tr.getChilds().indexOf(firstMultiple));

							done = true;
						}
					}


				}
			}
			//next, we try fraction multiplication
			if(resultingTerm == null){
				
				 firstMultiple = null;
				 secondMultiple = null;
				 resultingTerm = null;
				 
				 done = false;

				for(int i = 0; i<tr.getChilds().size(); i++){
					if(!done){
						
						Term kid = tr.getChilds().get(i);

						if(kid.isDecimal() || kid.isFraction()){
							if(firstMultiple == null){
								firstMultiple = kid;
							}
							else{
								secondMultiple = kid;

								Term result = Fraction.productOfNaturalFractions(firstMultiple, secondMultiple);
		
								if(result == null){break;}
								
								try {
									resultingTerm = (Term) tr.clone();
								} catch (CloneNotSupportedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								//remove the second addent and replace the first addent with our result
								resultingTerm = TimesMove.removeChild(resultingTerm, tr.getChilds().indexOf(secondMultiple));
								resultingTerm = TimesMove.replaceMultiplicativeTerm(resultingTerm, result, tr.getChilds().indexOf(firstMultiple));

								done = true;
							}
						}


					}
				}
			}

			return resultingTerm;
		}



	}

}
