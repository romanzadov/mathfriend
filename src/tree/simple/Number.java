package tree.simple;

import representTerms.StringRectangle;
import display.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Number extends SimpleTerm {
	
	private double absoluteValue;


	@Override
	public String toString(){
        String string = absoluteValue + "";
        if(string.contains(".")) {
            String[] parts = string.split("\\.");
            if (parts[1].equals("0")) {
                string = parts[0];
            }
            else if(parts[1].length() > 3) {
                string = parts[0]+"."+parts[1].substring(0,3) + "...";
            }
        }
        return string;
	}

	@Override
	public Number clone(){
		
		Number na = new Number(this.absoluteValue);
		return na;
	}
	
	public Number(double a){
		absoluteValue = a;
	}

    public Number(){};

    @Override
    public List<StringRectangle> getRelativeRectangles() {
        List<StringRectangle> rectangles = new ArrayList<StringRectangle>();
        rectangles.add(new StringRectangle(getContainer(), toString()));
        return rectangles;
    }

    public Rectangle getContainer() {
        String string = toString();
        return new Rectangle(string.length(), 1);
    }

	public double getAbsoluteValue(){
		return absoluteValue;
	}

	public boolean equals(Object a)
	{
		boolean ans = false;
		if(a.equals(absoluteValue)){ans=true;}
		return ans;
	}
	
	public boolean isNatural(){

        if(Math.floor(absoluteValue) == absoluteValue && absoluteValue >0  && !isNegative()){
            return true;
        }
		return false;
	}
	
    public boolean isWhole(){

		if(isNatural() || absoluteValue == 0){
			return true;
		}
        return false;
    }

    public boolean isInteger(){
        return Math.floor(absoluteValue) == absoluteValue;
	}

    public boolean isDecimal(){
		return !isInteger();
	}
}
