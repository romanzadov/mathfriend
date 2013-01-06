package tree.compound;

import java.util.ArrayList;
import java.util.List;

import tree.Term;
import tree.functions.Function;
import tree.functions.Times;

public class Fraction extends CompoundTerm{

	private Term numerator;
	private Term denominator;
	
	public static Fraction getOneOverTerm(final Term denominator) {
		final tree.simple.Number numerator = new tree.simple.Number(1);
		numerator.setParent(denominator.getParent());
		return new Fraction(numerator, denominator);
	}
	
	public static Fraction getIdentityFraction(final Term numerator) {
		final tree.simple.Number denominator = new tree.simple.Number(1);
		denominator.setParent(numerator.getParent());
		return new Fraction(numerator, denominator);
	}
	
	public Fraction(final Term numerator, final Term denominator) {
		super(Times.class);
		this.numerator = numerator;
		this.denominator = denominator;
		this.parent = numerator.getParent();
		numerator.setParent(this);
		denominator.setParent(this);
	}
	
	@Override
	public Function getFunction() {
		return new Times();
	}
	
	@Override
	public ArrayList<Term> getChildren() {
		ArrayList<Term> childs = new ArrayList<Term>();
		childs.add(numerator);
		childs.add(denominator);
		return childs;
	}
	
	@Override
	public String toString() {
		String html = "<span %s>%s</span>";
        List<String> classes = new ArrayList<String>();
        classes.add("term");
        if(!this.isSimple()) {
        	classes.add("compoundTerm");
        	classes.add("Fraction");
        }
        String content = "";

        if(this.hasParentheses()) {
        	classes.add("parantheses");
        }
        
        content += getContent(numerator);
        content += "<div class=\'operator fractionBar\'></div>";
        content += getContent(denominator);
        
        String classList = "";
        classList += "class=\'";
    	for(String c: classes) {
    		classList += c + " ";
    	}
    	classList += "\'";
    	
        return String.format(html, classList, content);     
	}
	
	public Term getNumerator() {
		return numerator;
	}
	
	public Term getDenominator() {
		return denominator;
	}
}
