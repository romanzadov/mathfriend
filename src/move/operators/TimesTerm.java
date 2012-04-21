package move.operators;

import representTerms.Image;
import tree.Term;
import tree.notsimple.Fraction;
import tree.functions.*;

public class TimesTerm {

	public void Times(Term tr, Term sel){

		if(tr.getFunction() instanceof Times || tr.getFunction() instanceof Divide){
			regularTimes(tr, sel);
		}

		else if(tr.getFunction() instanceof Plus || tr.getFunction() instanceof Minus){
			for(int i = 0; i< tr.getChildren().size(); i++){
				if(!(tr.getChildren().get(i) instanceof Function)){

					Term resel = null;
					try {
						resel = (Term)sel.clone();
					} catch (CloneNotSupportedException e) {}
					resel.setParent(sel.getParent());
					regularTimes(tr.getChildren().get(i), resel);
				}
			}
		}
		else{
			regularTimes(tr, sel);
		}


	}

	public void regularTimes(Term tr, Term sel){
		Times tm = new Times();
		if(tr.getFunction() instanceof Times || tr.getFunction() instanceof Divide){
			sel.setParent(tr);
			tm.setParent(tr);
			tr.getChildren().add(0,tm);
			tr.getChildren().add(0,sel);
		}
		else{
			Term mid = new Term();
			mid.setFunction(new Times());
			mid.setParent(tr.getParent());

			int trplace = tr.getParent().getChildren().indexOf(tr);

			mid.getChildren().add(sel);
			mid.getChildren().add(tm);
			mid.getChildren().add(tr);

			tr.setParent(mid);
			tm.setParent(mid);
			sel.setParent(mid);

			mid.getParent().getChildren().set(trplace, mid);
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
			for(int i = 0; i<tr.getChildren().size(); i++){
				if(!done){

					Term kid = tr.getChildren().get(i);

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
/*
							resultingTerm = TimesMove.removeChild(resultingTerm, tr.getChilds().indexOf(secondMultiple));

							resultingTerm = TimesMove.replaceMultiplicativeTerm(resultingTerm, result, tr.getChilds().indexOf(firstMultiple));
*/

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

				for(int i = 0; i<tr.getChildren().size(); i++){
					if(!done){

						Term kid = tr.getChildren().get(i);

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
	/*							resultingTerm = TimesMove.removeChild(resultingTerm, tr.getChilds().indexOf(secondMultiple));
								resultingTerm = TimesMove.replaceMultiplicativeTerm(resultingTerm, result, tr.getChilds().indexOf(firstMultiple));
*/
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
