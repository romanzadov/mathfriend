package tree.compound;

import java.util.ArrayList;
import java.util.List;

import tree.Term;
import tree.functions.Function;
import tree.simple.Number;

public class Fraction extends CompoundTerm{

	private Term numerator;
	private Term denominator;
	
	public static Fraction getOneOverTerm(final Term denominator) {
		final Number numerator = new Number(1);
		numerator.setParent(denominator.getParent());
		return new Fraction(numerator, denominator);
	}
	
	public static Fraction getIdentityFraction(final Term numerator) {
		final Number denominator = new Number(1);
		denominator.setParent(numerator.getParent());
		return new Fraction(numerator, denominator);
	}
	
	public Fraction(final Term numerator, final Term denominator) {
		super(Function.TIMES);
		this.numerator = numerator;
		this.denominator = denominator;
		denominator.setInverse(true);
		setParent(numerator.getParent());
		numerator.setParent(this);
		denominator.setParent(this);
	}
	
	@Override
	public Function getFunction() {
		return Function.TIMES;
	}
	
	@Override
	public ArrayList<Term> getChildren() {
		ArrayList<Term> childs = new ArrayList<Term>();
		childs.add(numerator);
		childs.add(denominator);
		return childs;
	}
	
	public String toHtml() {
		String html = "<span %s %s>%s</span>";
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
    	
    	String id = "data-id = \""+ hashCode()+"\"";
        return String.format(html, classList, id, content);     
	}
	
	public Term getNumerator() {
		return numerator;
	}
	
	public Term getDenominator() {
		return denominator;
	}
	
	public static Term getInverse(Term x) {
		if(!x.isFraction()) {
			return new Fraction(new Number(1), x);
		}
		
		Fraction f = (Fraction)x;
		if (f.getNumerator().isNumber(1)) {
			return f.getDenominator();
		}
		
		Fraction product = new Fraction(f.getDenominator(), f.getNumerator());
		product.setParent(null);
		return product;
	}
}
