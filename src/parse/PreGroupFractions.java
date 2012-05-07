package parse;

import java.util.ArrayList;

import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;
import tree.functions.Times;
import tree.simple.Placeholder;
import tree.simple.Number;

public class PreGroupFractions implements TreeFunction{


	public PreGroupFractions(Term tr){
		downwalk walk = new downwalk(tr, this);

	}

	public void performAction(Term tr) {


		if(tr != null && tr.getFunction() != null){
		/*	if(tr.getFunction() instanceof Times || tr.getFunction() instanceof Divide){

				Term[][] stacked = PutIntoMatrix(tr);

				Term mid = new Term();
				if(stacked.length>0 && stacked[0][0]!=null){
					mid = ChangeMid(stacked);
				}


				if(mid.getChildren().size()>0){
					tr.setChildren(new ArrayList<Term>());
					for(int i = 0; i<mid.getChildren().size(); i++){
						tr.getChildren().add(mid.getChildren().get(i));
					}
				}

			}*/




		}
	}


	public Term[][] PutIntoMatrix(Term tr){



		Term[][] stacked = new Term[tr.getChildren().size()][3];
		int[] position = new int[]{0,0};


		
		boolean hasdivide = false;
	/*	for(int i = 0; i< tr.getChildren().size(); i++){
			if(tr.getChildren().get(i) instanceof Divide){
				hasdivide = true;
			}

		}
*/
		if(hasdivide){
			for(int i = 0; i< tr.getChildren().size(); i++){
				Placeholder one = new Placeholder(1);
				Term piece = tr.getChildren().get(i);
				

				if(position[1]==0){

					/*if(piece instanceof Times){
					}*/

	/*				else if(piece instanceof Divide){
						stacked[position[0]][position[1]] = one;
						stacked[position[0]][1] = piece;
						position[1]=2;
					}

					else{
						stacked[position[0]][position[1]] = piece;
						position[1]=1;
						if(i!=tr.getChildren().size()-1 && (tr.getChildren().get(i+1) instanceof Times)){
							Divide div = new Divide();
							stacked[position[0]][1] = div;
							stacked[position[0]][2] = one;
							position[0]++;
							position[1]=0;
						}
						else if(i == tr.getChildren().size()-1){
							
							Divide div = new Divide();
							stacked[position[0]][1] = div;
							stacked[position[0]][2] = one;
							position[0]++;
							position[1]=0;
						}
					}
				}

				else if(position[1]==1){
					if(piece instanceof Times){
						Divide div = new Divide();
						stacked[position[0]][1] = div;
						stacked[position[0]][2] = one;
						position[0]++;
						position[1]=0;
					}
					else if (piece instanceof Divide){
						stacked[position[0]][position[1]] = piece;
						position[1]=2;
					}
					else{
						System.out.println("error in pregroupfractions. There's a term after term with no operator in between");
					}

				}

				else if (position[1]==2){
					if(piece instanceof Times){
						System.out.println("error in pregroupfractions: times");
					}
					else if(piece instanceof Divide){
						System.out.println("error in pregroupfractions: div");
					}
					else{
						stacked[position[0]][position[1]] = piece;
						position[0]++;
						position[1]=0;
					}*/

				}

			}
		}


		return stacked;
	}

	public Term ChangeMid(Term[][] stacked){

		Term mid = new Term();


		for(int i = 0; i< stacked.length; i++){
			if(stacked[i][0] != null){
/*
				if(stacked[i][0] instanceof Placeholder){

					Number one = new Number(1);
					Divide div = new Divide();
					Times tm = new Times();
					mid.getChildren().add(one);
					mid.getChildren().add(div);
					mid.getChildren().add(stacked[i][2]);
					mid.getChildren().add(tm);
				}
				else if(stacked[i][2] instanceof Placeholder){
					mid.getChildren().add(stacked[i][0]);
					Times tm = new Times();
					mid.getChildren().add(tm);
				}
				else{
					mid.getChildren().add(stacked[i][0]);
					mid.getChildren().add(stacked[i][1]);
					mid.getChildren().add(stacked[i][2]);
					Times tm = new Times();
					mid.getChildren().add(tm);
				}*/

			}
			/*else{
				if(mid.getChildren().get(mid.getChildren().size()-1) instanceof Times){
					mid.getChildren().remove(mid.getChildren().size()-1);
				}
			}*/
		}
		return mid;
	}



}
