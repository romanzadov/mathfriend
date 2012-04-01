package tree;
import java.util.ArrayList;

import parse.parenthesize;
import tree.operators.*;
import tree.simple.Number;
import tree.simple.Constant;
import tree.simple.SimpleTerm;
import tree.simple.variable;
//import android.util.Log;
import container.walks.newparens;
import display.rectangle;

public class treegen {
	
	static final String TAG = "treegen";

	public ArrayList<Term> orgconts = new ArrayList<Term>();		//where we store simple terms to organize them before putting them in terms.
	public ArrayList<Operator> ops = new ArrayList<Operator>();			//highest level operators
	public ArrayList<Term> contents = new ArrayList<Term>();


	public Term generatetree(ArrayList<SimpleTerm> simp) {

		Term firstterm = generatenode(simp);
		Term parent = new Term();
		rectangle cont = new rectangle();
		parent.container = cont;
		parent.getChildren().add(firstterm);
		firstterm.parent = parent;
		newparens np =new newparens(firstterm);
//		killparens kp = new killparens(firstterm);
		firstterm = simplecheck(firstterm);
		return firstterm;
	}

	public Term simplecheck(Term tr){
		
		if(tr.getChildren().size()==1&&tr.getChildren().get(0).getChildren().size()==0){
			tr.getChildren().get(0).issimple = true;
		}
		
		return tr;
	}
	
	public Term generatenode(ArrayList<SimpleTerm> simp){


		Term thisterm = new Term();

		thisterm = fullyreduced(simp, thisterm);

		ArrayList<int[]> pns = checkparens(simp);
		simp = removeexcessparens(simp, pns);

		setoperator(simp,pns);


		//check if simple term
		checksimples(thisterm);
		pns = checkparens(simp);		
		//after multiplication is added, parens have to be recalculated
		simp = removeexcessparens(simp, pns);
		pns = checkparens(simp);		

		Operator primary = pickhighestpriority(simp,pns);

		thisterm.operator = primary;

		rephrase(simp,primary);
		thisterm = setnode(thisterm);
		checksimples(thisterm);

		digdown(thisterm);

		return thisterm;
	}


	public Term fullyreduced(ArrayList<SimpleTerm> simp, Term tr2){

		if(simp.size() == 1){
			simp.get(0).parent = tr2;
			tr2.getChildren().add(simp.get(0));
			simp.get(0).simples.add(simp.get(0));
		}

		return tr2;
	}

	public ArrayList<SimpleTerm> removeexcessparens(ArrayList<SimpleTerm> simp, ArrayList<int[]> paren){
		boolean done = false;
		boolean didsomething = false;
		while(done == false){
			paren = checkparens(simp);
			for(int i = 0; i<paren.size();i++){
				if(paren.get(i)[0]==0 && paren.get(i)[1]==simp.size()-1){

					simp.remove(0);
					int last = simp.size();
					simp.remove(last-1);
					paren.remove(i);
					didsomething =true;
				}
				else if(paren.get(i)[1]-paren.get(i)[0]==1){
					simp.remove(paren.get(i)[1]);
					simp.remove(paren.get(i)[0]);
					paren.remove(i);
					didsomething = true;
				}
			}

			if(didsomething==true)
			{didsomething=false;}
			else{done = true;}
		}


		return simp;
	}

	public void rephrase(ArrayList<SimpleTerm> simp, Operator primary){
		
		
		int start = 0;
		if(simp.size() == 1){
		//	plus pls = new plus();
		//	simp.get(0).operator = pls;
		//	term ph = new term();
		//	ph.simples.add(simp.get(0));
		//	orgconts.add(ph);
		}
		else{
			for(int i=0;i<ops.size();i++){
				Term ph = new Term();
				
				for(int j=start;j<ops.get(i).charpos;j++){
					ph.simples.add(simp.get(j));
				}
				
				
				orgconts.add(ph);
				Term pho = new Term();
				pho.simples.add(ops.get(i));
				orgconts.add(pho);
				start = ops.get(i).charpos+1;

			}
			if(ops.size()>0){
				int last = ops.size()-1;
				if(ops.get(last).charpos<simp.size()){
					Term ph = new Term();
					for(int i = ops.get(last).charpos+1; i<simp.size();i++){
						ph.simples.add(simp.get(i));
					}
					orgconts.add(ph);
				}
			}
		}
	}

	public ArrayList<int[]> checkparens(ArrayList<SimpleTerm> simp){
		parenthesize par = new parenthesize();	
		ArrayList<int[]> paren = par.organize(simp);
		return paren;
	}

	public Operator pickhighestpriority(ArrayList<SimpleTerm> simp,ArrayList<int[]> parens){
		int primaryspot = 0;
		double orderofoperation = -10;
		for(int i=0; i<simp.size();i++){
			if(simp.get(i) instanceof Operator){
				Operator a = (Operator)simp.get(i);
				if(a.orderofoperation> orderofoperation){
					primaryspot = i;
					orderofoperation = a.orderofoperation;
				}
			}
			//don't look for operators inside parens
			else if(simp.get(i) instanceof Parens){
				for(int j=0;j<parens.size();j++){
					if(parens.get(j)[0]==i){ i=parens.get(j)[1]; }
				}
			}


		}
		Operator primary;
		try {
			primary = (Operator)simp.get(primaryspot);
		} catch (Exception e) {
			primary = new Plus();
		}
		
		for(int i=0; i<simp.size();i++){
			if(simp.get(i) instanceof Parens){
				for(int j=0;j<parens.size();j++){
					if(parens.get(j)[0]==i){ i=parens.get(j)[1]; }
				}		
			}
			else if(simp.get(i) instanceof Operator){
				Operator a = (Operator)simp.get(i);
				if(a.orderofoperation==primary.orderofoperation){
					a.charpos=i;
					ops.add(a);
				}
			}
		}


		return primary;
	}


	public void digdown(Term thisterm){
		for(int i = 0;i< contents.size();i++){		//for any non simple term

			Term child = new Term();
			treegen tr = new treegen();

			if(contents.get(i).issimple==false){
				child = tr.generatenode(contents.get(i).simples);}
			else if(contents.get(i).issimple){
				child = contents.get(i);
			}
			child.parent = thisterm;
			thisterm.getChildren().add(child);
		}

	}

	public void checksimples(Term thisterm){
		if(thisterm.operator instanceof Negative){
			thisterm.setNegative(true);
		}
		
		for(int i =0; i<orgconts.size();i++){
			if(orgconts.get(i).simples.size()==1){
				SimpleTerm a = orgconts.get(i).simples.get(0);
				orgconts.set(i, a);
				orgconts.get(i).issimple = true;
			}
			else if(orgconts.get(i).simples.size()==3 &&
					orgconts.get(i).simples.get(0) instanceof Parens &&
					orgconts.get(i).simples.get(2) instanceof Parens){
				
				SimpleTerm a = orgconts.get(i).simples.get(1);
				a.issimple = true;
				a.hasParentheses = true;
				orgconts.set(i, a);
				
			}
			
			else {
				int parennumber=0;
				for(int j = 0; j<orgconts.get(i).simples.size();j++){
					if(orgconts.get(i).simples.get(j).equals('(')||
							orgconts.get(i).simples.get(j).equals(')')	){
						parennumber++;
					}
				}
				if(orgconts.get(i).simples.size()-parennumber<2){
					orgconts.get(i).issimple = true;
				}
			}
		}
	}

	public void setoperator(ArrayList<SimpleTerm> simp,ArrayList<int[]> pns){

		//first set basics
		//then override minuses with negatives as needed

		for(int i = 0; i<simp.size(); i++){
			if(simp.get(i) instanceof Operator){

				if(simp.get(i).equals("+")){
					Plus a = new Plus();
					a.charpos = ((Operator)simp.get(i)).charpos;
					a.thisvalue = ((Operator)simp.get(i)).thisvalue;
					a.valueString =  ((Operator)simp.get(i)).valueString;
					simp.set(i,a);
				}
				if(simp.get(i).equals("-")){
					Minus a = new Minus();
					a.charpos = ((Operator)simp.get(i)).charpos;
					a.thisvalue = ((Operator)simp.get(i)).thisvalue;
					a.valueString =  ((Operator)simp.get(i)).valueString;
					simp.set(i,a);
				}
				if(simp.get(i).equals("*")){
					Times a = new Times();
					a.charpos = ((Operator)simp.get(i)).charpos;
					a.thisvalue = ((Operator)simp.get(i)).thisvalue;
					a.valueString =  ((Operator)simp.get(i)).valueString;
					simp.set(i,a);
				}
				if(simp.get(i).equals("/")){
					Divide a = new Divide();
					a.charpos = ((Operator)simp.get(i)).charpos;
					a.thisvalue = ((Operator)simp.get(i)).thisvalue;
					a.valueString =  ((Operator)simp.get(i)).valueString;
					simp.set(i,a);
				}
				if(simp.get(i).equals("^")){
					Exponent a = new Exponent();
					a.charpos = ((Operator)simp.get(i)).charpos;
					a.thisvalue = ((Operator)simp.get(i)).thisvalue;
					a.valueString =  ((Operator)simp.get(i)).valueString;
					simp.set(i,a);
				}

				if(simp.get(i).equals("=")){
					Equality a = new Equality();
					a.charpos = ((Operator)simp.get(i)).charpos;
					a.thisvalue = ((Operator)simp.get(i)).thisvalue;
					a.valueString =  ((Operator)simp.get(i)).valueString;
					simp.set(i,a);
				}
			}


		}
		//reset minuses to negatives
		for(int i = 0; i<simp.size(); i++){
			if(simp.get(i) instanceof Minus){

				if(i==0||simp.get(i-1) instanceof Equality
						||simp.get(i-1).equals("(")
						||simp.get(i-1) instanceof Operator){

					Negative neg = new Negative();
					neg.charpos = ((Operator)simp.get(i)).charpos;
					neg.valueString = "-";
					simp.set(i, neg);
					
					//add parens around negative
					//unless there's an exponent afterwards
					boolean add = true;
					
					
					if(simp.size()>i+2){
						if(simp.get(i+2) instanceof Exponent){
							add = false;
						}
						
					}
					if(i>0){
						if(simp.get(i-1) instanceof Exponent){
							add = true;
						}
					}
					
					if(simp.size()>i+2){
						if(simp.get(i+2) instanceof Parens){
							//if parens, find end
							int end = 0;
							ArrayList<int[]> pans = checkparens(simp);
							for(int j =0; j<pans.size(); j++){
								if(pans.get(j)[0]==i+2){
									end = pans.get(j)[1];
								}
							}
							if(end!=0){
								if(simp.size()>end+1){
									//the the one after parens is an exponent
									//check that the one before isn't
									if(simp.get(end+1) instanceof Exponent){
										add = false;
									}
								}
							}
							if(i-1>0){
								if(simp.get(i-1) instanceof Exponent){
									add = true;
								}
							}
						}
					}

					if(add){simp = addParensAroundNegatives(simp, pns, i);}

				}
			}


		}

		//add invisible multiplication
		for(int i = 0; i<simp.size()-1; i++){
			if(simp.get(i).rmult==true&&simp.get(i+1).lmult==true){
				Times a = new Times();
				a.valueString = "*";
				simp.add(i+1,a);
			}

		}


	}


	public Term setnode(Term thisterm){

		contents = orgconts;
		return thisterm;
	}

	public ArrayList<SimpleTerm> addParensAroundNegatives(ArrayList<SimpleTerm> simp, ArrayList<int[]> pns, int positionOfNegative){
		pns = checkparens(simp);
		
		Parens left = new Parens();
		left.value = '(';
		left.valueString = "(";
		left.lmult = true;
		Parens right = new Parens();
		right.value = ')';
		right.valueString = ")";
		right.rmult = true;
		
		//add left parens before the negative
		simp.add( positionOfNegative, left);
		
		int newPositionOfNegative=positionOfNegative+1; //because we added a parens, the position has moved

		//add right parens after the term the negative applies to
			
			//if it applies to a parens, find its end
		if(simp.get(newPositionOfNegative+1) instanceof Parens){
			int end = 0;
			for(int i = 0; i<pns.size(); i++){
				if(pns.get(i)[0] == positionOfNegative+1){
					end = pns.get(i)[1]+1;
				}
			}
			simp.add(end+1, right);
		}
			//else, it just applies to a number or variable
		else{
			simp.add(newPositionOfNegative+2, right);
		}
//		Log.d(TAG, simp.toString());
		return simp;
	}
	
	public ArrayList<SimpleTerm>  addpns(ArrayList<SimpleTerm> simp, ArrayList<int[]> pns, int st){
		//first, reset pns
		pns = checkparens(simp);

		//if we start with a parens, find its end

		if(simp.get(st) instanceof Parens){
			int end = 0;
			for(int i = 0; i<pns.size(); i++){
				if(pns.get(i)[0]==st){
					end = pns.get(i)[1];
				}
			}
			Parens left = new Parens();
			left.value = '(';
			left.valueString = "(";
			left.lmult = true;
			Parens right = new Parens();
			right.value = ')';
			right.valueString = ")";
			right.rmult = true;
			simp.add(st-2,left);
			simp.add(end+2, right);
		}
		else if(simp.get(st) instanceof Number || simp.get(st) instanceof variable
				|| simp.get(st) instanceof Constant){
			Parens left = new Parens();
			left.value = '(';
			left.valueString = "(";
			left.lmult = true;
			Parens right = new Parens();
			right.value = ')';
			right.valueString = ")";
			right.rmult = true;
			simp.add(st-2,left);
			simp.add(st+2, right);
		}


		return simp;
	}


}