package tree.compound;

import java.util.ArrayList;
import java.util.List;

import tree.Term;
import tree.TermUtil;
import tree.functions.Function;

public class CompoundTerm extends Term {

	private Function function;
	private ArrayList<Term> children = new ArrayList<Term>();


    public CompoundTerm(Function function){
        this.function = function;
    }

    public void addChild(Term child) {
    	child.setParent(this);
        children.add(child);
    }

    @Override
    public String toString() {
    	String content = "";
    	for(int i = 0; i<this.getChildren().size(); i++){
        	if (i == 0 && this.getChildren().get(0).isNegative()) {
        		content += "-";
        	}
            content += getChildren().get(i).toString();
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
            	} else if (Function.TIMES.equals(function)) {
            		Term nextChild = this.getChildren().get(i + 1);
            		if (nextChild.isInverse()) {
            			operator = "/";
            		} else {
            			operator = "*";
            		}
            	}
            	content +=  operator;
            }
        }
    	return content;
    }
    
	public String toHtml(){
		
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
        if(this.hasParentheses()) {
        	classes.add("parantheses");
        	content += "(";
        }
        
        for(int i = 0; i<this.getChildren().size(); i++){
        	Term child = getChildren().get(i);

        	if (i == 0 && this.getChildren().get(0).isNegative()) {
        		content += "-";
        	} else if (getChildren().get(i).isNegative() && this.function.equals(Function.EQUALS)) {
        		content += "-";
        	}
            content += getContent(child);
            if(i < this.getChildren().size() - 1) {
        		Term nextChild = this.getChildren().get(i + 1);
            	String operatorContent = "";
            	if (Function.EQUALS.equals(function)) {
            		operatorContent = "=";
            	} else if (Function.PLUS.equals(function)) {
            		if (nextChild.isNegative()) {
            			operatorContent = "-";
            		} else {
            			operatorContent = "+";
            		}
            	} else if (Function.TIMES.equals(function)) {
            		if (TermUtil.canBeMultiplied(child, nextChild)) {
            			operatorContent = "*";
            		}
            	}
            	if (operatorContent != "") {
            		String operator = "<span class=\'operator\' %s %s >%s</span>";
                	String id = "data-id = \""+this.hashCode()+"\"";
                	String operatorId = "data-operator-id = \""+ i +"\"";
            		content += String.format(operator, id, operatorId, operatorContent);
            	}
            }
        }
        
        if(this.hasParentheses()) {
        	content += ")";
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
			return ((CompoundTerm)child).toHtml();
		}
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
	
	public void removeChild(Term child) {
		children.remove(child);
		child.setParent(null);
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

}

