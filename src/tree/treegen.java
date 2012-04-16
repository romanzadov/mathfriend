package tree;
import java.util.ArrayList;

import parse.ParenthesisUtil;
import parse.PreSimpleTerm;
import tree.operators.*;
import tree.simple.Number;
import tree.simple.Constants;
import tree.simple.SimpleTerm;
import tree.simple.Variable;
//import android.util.Log;


public class TreeGen {

	public ArrayList<Term> orgconts = new ArrayList<Term>();		//where we store simple terms to organize them before putting them in terms.
	public ArrayList<Operator> ops = new ArrayList<Operator>();			//highest level operators
	public ArrayList<Term> contents = new ArrayList<Term>();


	public Term generateTree(ArrayList<PreSimpleTerm> preSimpleTerms) {

		Term firstterm = generatenode(preSimpleTerms);
//		newparens np =new newparens(firstterm);
		return firstterm;
	}


	
	public Term generatenode(ArrayList<PreSimpleTerm> simp){


		Term term = new Term();

		ArrayList<int[]> pns = ParenthesisUtil.getParenthesisGroups(simp);
	//	simp = removeExcessParens(simp, pns);



		//check if simple term
		checksimples(term);
		pns = ParenthesisUtil.getParenthesisGroups(simp);
		//after multiplication is added, parens have to be recalculated
		simp = removeExcessParens(simp, pns);
		pns = ParenthesisUtil.getParenthesisGroups(simp);

		Operator primary = pickhighestpriority(simp,pns);

		term.setOperator(primary);

		rephrase(simp,primary);
		term = setnode(term);
		checksimples(term);

		digdown(term);

		return term;
	}

	public ArrayList<SimpleTerm> removeExcessParens(ArrayList<SimpleTerm> simp, ArrayList<int[]> paren){
		boolean done = false;
		boolean didsomething = false;
		while(done == false){
			paren = ParenthesisUtil.getParenthesisGroups(simp);
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

	public Operator pickhighestpriority(ArrayList<SimpleTerm> simp,ArrayList<int[]> parens){
		int primaryspot = 0;
		double orderofoperation = Double.MIN_VALUE;
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
			TreeGen tr = new TreeGen();

			if(contents.get(i).isSimple()==false){
				child = tr.generatenode(contents.get(i).simples);}
			else if(contents.get(i).isSimple()){
				child = contents.get(i);
			}
			child.setParent(thisterm);
			thisterm.getChildren().add(child);
		}

	}

	public void checksimples(Term term){
		if(term.getOperator() instanceof Negative){
			term.setNegative(true);
		}
		
		for(int i =0; i<orgconts.size();i++){
			if(orgconts.get(i).simples.size()==1){
				SimpleTerm a = orgconts.get(i).simples.get(0);
				orgconts.set(i, a);
		//		orgconts.get(i).isSimple() = true;
			}
			else if(orgconts.get(i).simples.size()==3 &&
					orgconts.get(i).simples.get(0) instanceof Parens &&
					orgconts.get(i).simples.get(2) instanceof Parens){
				
				SimpleTerm a = orgconts.get(i).simples.get(1);
		//		a.isSimple() = true;
				a.setHasParentheses(true);
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
/*				if(orgconts.get(i).simples.size()-parennumber<2){
					orgconts.get(i).issimple = true;
				}*/
			}
		}
	}

	public Term setnode(Term thisterm){

		contents = orgconts;
		return thisterm;
	}

	public ArrayList<SimpleTerm> addParensAroundNegatives(ArrayList<SimpleTerm> simp, ArrayList<int[]> pns, int positionOfNegative){
		pns = ParenthesisUtil.getParenthesisGroups(simp);
		
		Parens left = new Parens();
		left.value = '(';
		left.setValueString("(");
		Parens right = new Parens();
		right.value = ')';
		right.setValueString(")");

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
		pns = ParenthesisUtil.getParenthesisGroups(simp);

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
			left.setValueString("(");
			Parens right = new Parens();
			right.value = ')';
			right.setValueString(")");
			simp.add(st-2,left);
			simp.add(end+2, right);
		}
		else if(simp.get(st) instanceof Number || simp.get(st) instanceof Variable
				|| simp.get(st) instanceof Constants){
			Parens left = new Parens();
			left.value = '(';
			left.setValueString("(");
			Parens right = new Parens();
			right.value = ')';
			right.setValueString(")");
			simp.add(st-2,left);
			simp.add(st+2, right);
		}


		return simp;
	}


}
