package parse;

import java.util.ArrayList;

import tree.downwalk;
import tree.term;
import tree.downwalk.TreeFunction;
import tree.operators.divide;
import tree.operators.operator;
import tree.operators.parens;
import tree.operators.times;
import tree.simple.Placeholder;
import tree.simple.Number;

public class PreGroupFractions implements TreeFunction{


	public PreGroupFractions(term tr){
		downwalk walk = new downwalk(tr, this);

	}

	public void performAction(term tr) {


		if(tr != null && tr.operator != null){
			if(tr.operator instanceof times || tr.operator instanceof divide){

				term[][] stacked = PutIntoMatrix(tr);

				term mid = new term();
				if(stacked.length>0 && stacked[0][0]!=null){
					mid = ChangeMid(stacked);
				}


				if(mid.getChilds().size()>0){	
					tr.setChilds(new ArrayList<term>());
					for(int i = 0; i<mid.getChilds().size(); i++){
						tr.getChilds().add(mid.getChilds().get(i));
					}
				}

			}




		}
	}


	public term[][] PutIntoMatrix(term tr){



		term[][] stacked = new term[tr.getChilds().size()][3];
		int[] position = new int[]{0,0};


		
		boolean hasdivide = false;
		for(int i = 0; i< tr.getChilds().size(); i++){
			if(tr.getChilds().get(i) instanceof divide){
				hasdivide = true;
			}

		}

		if(hasdivide){
			for(int i = 0; i< tr.getChilds().size(); i++){
				Placeholder one = new Placeholder(1);
				term piece = tr.getChilds().get(i);
				

				if(position[1]==0){

					if(piece instanceof times){ 
					}

					else if(piece instanceof divide){
						stacked[position[0]][position[1]] = one;
						stacked[position[0]][1] = piece;
						position[1]=2;
					}

					else{
						stacked[position[0]][position[1]] = piece;
						position[1]=1;
						if(i!=tr.getChilds().size()-1 && (tr.getChilds().get(i+1) instanceof times)){
							divide div = new divide();
							stacked[position[0]][1] = div;
							stacked[position[0]][2] = one;
							position[0]++;
							position[1]=0;
						}
						else if(i == tr.getChilds().size()-1){
							
							divide div = new divide();
							stacked[position[0]][1] = div;
							stacked[position[0]][2] = one;
							position[0]++;
							position[1]=0;
						}
					}
				}

				else if(position[1]==1){
					if(piece instanceof times){
						divide div = new divide();
						stacked[position[0]][1] = div;
						stacked[position[0]][2] = one;
						position[0]++;
						position[1]=0;
					}
					else if (piece instanceof divide){
						stacked[position[0]][position[1]] = piece;
						position[1]=2;
					}
					else{
						System.out.println("error in pregroupfractions. There's a term after term with no operator in between");
					}

				}

				else if (position[1]==2){
					if(piece instanceof times){
						System.out.println("error in pregroupfractions: times");
					}
					else if(piece instanceof divide){
						System.out.println("error in pregroupfractions: div");
					}
					else{
						stacked[position[0]][position[1]] = piece;
						position[0]++;
						position[1]=0;
					}

				}

			}
		}


		return stacked;
	}

	public term ChangeMid(term[][] stacked){

		term mid = new term();


		for(int i = 0; i< stacked.length; i++){
			if(stacked[i][0] != null){

				if(stacked[i][0] instanceof Placeholder){

					Number one = new Number(1);
					divide div = new divide();
					times tm = new times();
					mid.getChilds().add(one);
					mid.getChilds().add(div);
					mid.getChilds().add(stacked[i][2]);
					mid.getChilds().add(tm);
				}
				else if(stacked[i][2] instanceof Placeholder){
					mid.getChilds().add(stacked[i][0]);
					times tm = new times();
					mid.getChilds().add(tm);
				}
				else{
					mid.getChilds().add(stacked[i][0]);
					mid.getChilds().add(stacked[i][1]);
					mid.getChilds().add(stacked[i][2]);
					times tm = new times();
					mid.getChilds().add(tm);
				}

			}
			else{
				if(mid.getChilds().get(mid.getChilds().size()-1) instanceof times){
					mid.getChilds().remove(mid.getChilds().size()-1);
				}
			}
		}
		return mid;
	}



}
