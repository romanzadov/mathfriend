package tree.compound;

import java.util.ArrayList;
import java.util.List;

import container.walks.AssignScreenPositions;

import representTerms.StringRectangle;
import tree.Term;
import tree.functions.*;

public class CompoundTerm extends Term {

	private Function function;
	private ArrayList<Term> children = new ArrayList<Term>();


    public CompoundTerm(Function function){
        this.function = function;
    }

    public void addChild(Term child) {
        children.add(child);
    }

	@Override
	public String toString(){
		
        String html = "<span %s %s>%s</span>";
        List<String> classes = new ArrayList<String>();
        classes.add("term");
        if(!this.isSimple()) {
        	classes.add("compoundTerm");
        	classes.add(function.name());
        }
        String content = "";
        if (this.isInverse()) {
        	classes.add("inverse");
        }
        if(this.hasParentheses()) {
        	classes.add("parantheses");
        }
        
        for(int i = 0; i<this.getChildren().size(); i++){
        	if (i == 0 && this.getChildren().get(0).isNegative()) {
        		content += "-";
        	}
            content += getContent(getChildren().get(i));
            if(i < this.getChildren().size() - 1) {
            	String operator = "";
            	if (Function.EQUALS.equals(function)) {
            		operator = "=";
            	} else if (Function.PLUS.equals(function)) {
            		Term nextChild = this.getChildren().get(i + 1);
            		if (nextChild.isNegative()) {
            			operator = "-";
            		} else {
            			operator = "+";
            		}
            	} 
            	if (operator != "") {
            		content += "<span class=\'operator\'>" + operator + "</span>";
            	}
            }
        }
        
        String classList = "";
        classList += "class=\'";
    	for(String c: classes) {
    		classList += c + " ";
    	}
    	classList += "\'";
    	String id = "data-id = \""+this.hashCode()+"\"";
        return String.format(html, classList, id, content);        
	}
	
	protected String getContent(Term child) {
		if (child.isSimple()) {
	        String html = "<span %s %s>" + child.toString() + "</span>";
	        List<String> classes = new ArrayList<String>();
	        classes.add("term");
	        classes.add(child.getClass().getSimpleName());
	        if (child.isInverse()) {
	        	classes.add("inverse");
	        }
	        if(child.hasParentheses()) {
	        	classes.add("parantheses");
	        }	
	        String classList = "";
	        classList += "class=\'";
	    	for(String c: classes) {
	    		classList += c + " ";
	    	}
	    	classList += "\'";
	    	String id = "data-id = \""+child.hashCode()+"\"";
	        return String.format(html, classList, id);   
		} else {
			return child.toString();
		}
	}

	public boolean isSimpleFraction(){
		CompoundTerm tr = this;
		boolean fraction = false;
		/*if (tr.getFunction() instanceof Divide && tr.getChildren().size() == 3 &&
				tr.getChildren().get(0).isInteger() && tr.getChildren().get(2).isInteger()){
			fraction = true;
		}*/
		return fraction;
	}

    public void insertChild(int index, Term child) {
        children.add(index, child);
        child.setParent(this);
    }

    public void setChild(int index, Term child) {
        children.set(index, child);
        child.setParent(this);
    }

	public void setChildren(ArrayList<Term> children) {
		this.children = children;
		for(Term child: children) {
			child.setParent(this);
		}
	}

	public ArrayList<Term> getChildren() {
		return children;
	}

    public ArrayList<CompoundTerm> getCompoundChildren() {
        ArrayList<CompoundTerm> compoundTerms = new ArrayList<CompoundTerm>();
        for(Term child: children) {
            if(child instanceof CompoundTerm) {
                compoundTerms.add((CompoundTerm)child);
            }
        }
        return compoundTerms;
    }

	public void setScreenPositions(ArrayList<StringRectangle> screenPositions){
		//set the position of the containers of the term as they are drawn on a screen.

		AssignScreenPositions asp = new AssignScreenPositions(this, screenPositions);
	}

	public CompoundTerm getResultOfOperation(){
		return getResultOfBasicOperation();
	}


	private CompoundTerm getResultOfBasicOperation(){

		/*if(!this.isSimple()){

			CompoundTerm result = this.getFunction().simpleOperation(this);

			return result;}
		else{
			return null;
		}*/
		return null;
	}



    public Function getFunction() {
        return function;
    }

    @Override
    protected StringRectangle getStringRectangle() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    
}

