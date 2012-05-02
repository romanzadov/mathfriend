package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parse.ParenthesisUtil;
import parse.PreSimpleTerm;
import tree.functions.*;


public class TermContsructionUtil {

    public ArrayList<Term> orgconts = new ArrayList<Term>();        //where we store simple terms to organize them before putting them in terms.
    public ArrayList<Function> ops = new ArrayList<Function>();            //highest level operators
    public ArrayList<Term> contents = new ArrayList<Term>();


    /*public Term generatenode(List<PreSimpleTerm> simp){


         Term term = new Term();

     //	simp = removeExcessParens(simp, pns);



         //check if simple term
         checksimples(term);
         //after multiplication is added, parens have to be recalculated
     //	simp = removeExcessParens(simp, pns);

         Function primary = getHighestPriorityFunction(simp);

         term.setFunction(primary);

         rephrase(simp,primary);
         term = setnode(term);
         checksimples(term);

         digdown(term);

         return term;
     }*/

    /*public ArrayList<SimpleTerm> removeExcessParens(List<SimpleTerm> simp){
         boolean done = false;
         boolean didsomething = false;
         while(done == false){
             Map<Integer, Integer> paren = ParenthesisUtil.getParenthesisGroups(simp);
             *//*for(int i = 0; i<paren.size();i++){
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
			}*//*

			if(didsomething==true)
			{didsomething=false;}
			else{done = true;}
		}


		return simp;
	}*/

/*	public void rephrase(ArrayList<SimpleTerm> simp, Function primary){
		
		
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
	}*/

    public static Class<? extends Function> getHighestPriorityFunction(List<PreSimpleTerm> preSimpleTerms) {
        Class<? extends Function> primaryPreFunction = null;
        int order = Integer.MAX_VALUE;
        Map<Integer, Integer> parentheses = ParenthesisUtil.getParenthesisGroups(preSimpleTerms);

        for (int i = 0; i < preSimpleTerms.size(); i++) {
            PreSimpleTerm preSimpleTerm = preSimpleTerms.get(i);
            if (preSimpleTerm.getType().equals(PreSimpleTerm.Type.FUNCTION)) {

                if (Function.getOrderOfOperation(preSimpleTerm) < order) {
                    primaryPreFunction = preSimpleTerm.getFunctionType().getFunction();
                    order = Function.getOrderOfOperation(preSimpleTerm);
                }
            }

            //check for invisible multiplication
            if (hasInvisibleMultiplication(preSimpleTerm, preSimpleTerms)) {
                if (Function.getOrderOfOperation(Times.class) < order) {
                    primaryPreFunction = Times.class;
                    order = Function.getOrderOfOperation(Times.class);
                }
            }

            //don't look for operators inside parentheses
            else if (preSimpleTerm.getType().equals(PreSimpleTerm.Type.PARENTHESES)) {
                i = parentheses.get(i);
            }
        }

        return primaryPreFunction;
    }

    public static List<PreSimpleTermGrouping> getGroupings(List<PreSimpleTerm> preSimpleTerms, Class<? extends Function> function) {
        List<PreSimpleTermGrouping> groupings = new ArrayList<PreSimpleTermGrouping>();

        List<Integer> groupEndpoints = new ArrayList<Integer>();

        for (PreSimpleTerm preSimpleTerm : preSimpleTerms) {
            int breakPoint = -1;
            boolean breakPointFound = false;
            if (PreSimpleTerm.Type.FUNCTION.equals(preSimpleTerm.getType()) && function.equals(preSimpleTerm.getFunctionType().getFunction())) {
                breakPointFound = true;
                breakPoint = preSimpleTerms.indexOf(preSimpleTerm);
            }
            //if our function is multiplication, check for implicit multiplications like 2x
            if (function == Times.class && !breakPointFound) {
                breakPointFound = hasInvisibleMultiplication(preSimpleTerm, preSimpleTerms);
                breakPoint = preSimpleTerms.indexOf(preSimpleTerm) + 1;
            }

            if (breakPointFound || preSimpleTerms.size() == preSimpleTerms.indexOf(preSimpleTerm)) {
                groupEndpoints.add(breakPoint);
            }
        }

        for (Integer groupEnd : groupEndpoints) {
            List<PreSimpleTerm> group = new ArrayList<PreSimpleTerm>();
            if (groupEndpoints.indexOf(groupEnd) == 0) {
                group = preSimpleTerms.subList(0, groupEnd);
            } else {
                group = preSimpleTerms.subList(groupEndpoints.get(groupEndpoints.indexOf(groupEnd) - 1), groupEnd);
            }

            groupings.add(getPreSimpleTermGrouping(group));
        }

        int lastBreakpoint = groupEndpoints.get(groupEndpoints.size() - 1);
        int endOfList =  preSimpleTerms.size();
        groupings.add(getPreSimpleTermGrouping(preSimpleTerms.subList(lastBreakpoint, endOfList)));

        return groupings;
    }

    private static PreSimpleTermGrouping getPreSimpleTermGrouping(List<PreSimpleTerm> group) {
        boolean isNegative = false;
        boolean isInverse = false;
        boolean hasParentheses = false;
        boolean ignoreFirst = false;
        if (group.size() > 1) {
            PreSimpleTerm firstTerm = group.get(0);
            PreSimpleTerm secondTerm = group.get(1);
            PreSimpleTerm lastTerm = group.get(group.size() - 1);
            if (PreSimpleTerm.FunctionType.MINUS.equals(firstTerm.getFunctionType()) || PreSimpleTerm.FunctionType.NEGATIVE.equals(firstTerm.getFunctionType())) {
                isNegative = true;
                group.remove(firstTerm);
                if (PreSimpleTerm.Type.PARENTHESES.equals(secondTerm.getType()) && PreSimpleTerm.Type.PARENTHESES.equals(lastTerm.getType())) {
                    hasParentheses = true;
                    group.remove(secondTerm);
                    group.remove(lastTerm);
                }
            } else if (PreSimpleTerm.FunctionType.DIVIDE.equals(firstTerm.getFunctionType())) {
                isInverse = true;
                group.remove(firstTerm);
                if (PreSimpleTerm.Type.PARENTHESES.equals(secondTerm.getType()) && PreSimpleTerm.Type.PARENTHESES.equals(lastTerm.getType())) {
                    hasParentheses = false;//example: 2/(3x-4).  These are probably parentheses for parsing, not for the equation. I will remove them
                    group.remove(secondTerm);
                    group.remove(lastTerm);
                }
            } else if (PreSimpleTerm.Type.PARENTHESES.equals(firstTerm.getType()) && PreSimpleTerm.Type.PARENTHESES.equals(lastTerm.getType())) {
                hasParentheses = true;
                group.remove(firstTerm);
                group.remove(lastTerm);
            } else if (PreSimpleTerm.Type.FUNCTION.equals(firstTerm.getType()) && firstTerm.getFunctionType().getFunction() == AdvancedFunction.class) {
                group.remove(firstTerm);
                if (PreSimpleTerm.Type.PARENTHESES.equals(secondTerm.getType()) && PreSimpleTerm.Type.PARENTHESES.equals(lastTerm.getType())) {
                    group.remove(secondTerm);
                    group.remove(lastTerm);
                } else {
                    throw new RuntimeException("advanced function without a parentheses afterwards");
                }
            } else {
                //trim operators
                if (PreSimpleTerm.Type.FUNCTION.equals(firstTerm.getType())) {
                    ignoreFirst = true;
                }
            }

        }
        if(ignoreFirst) {
            return new PreSimpleTermGrouping(group.subList(1, group.size()), isNegative, isInverse, hasParentheses);
        } else {
            return new PreSimpleTermGrouping(group, isNegative, isInverse, hasParentheses);
        }

    }

    private static boolean hasInvisibleMultiplication(PreSimpleTerm preSimpleTerm, List<PreSimpleTerm> preSimpleTerms) {
        int index = preSimpleTerms.indexOf(preSimpleTerm);

        if (index == preSimpleTerms.size() - 1) {
            return false;
        } else {
            PreSimpleTerm nextTerm = preSimpleTerms.get(index + 1);
            return preSimpleTerm.isRightMultiplied() && nextTerm.isLeftMultiplied();
        }
    }

    /*public void digdown(Term thisterm){
            for(int i = 0;i< contents.size();i++){		//for any non simple term

                Term child = new Term();
                TermContsructionUtil tr = new TermContsructionUtil();

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
            if(term.getFunction() instanceof Negative){
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
    *//*				if(orgconts.get(i).simples.size()-parennumber<2){
					orgconts.get(i).issimple = true;
				}*//*
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
		else if(simp.get(st) instanceof Number || simp.get(st) instanceof Variables
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

*/
}
