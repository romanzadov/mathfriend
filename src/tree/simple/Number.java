package tree.simple;

import representTerms.Settings;
import representTerms.StringRectangle;
import display.Rectangles;

public class Number extends SimpleTerm {
	
	public double absoluteValue;


	@Override
	public String toString(){
        String string = absoluteValue + "";
        if(string.contains(".")) {
            String[] parts = string.split("\\.");
            if (parts[1].equals("0")) {
                string = parts[0];
            }
            else if(parts[1].length() > 3) {
                string = parts[0]+"."+parts[1].substring(0,2) + "...";
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

	public StringRectangle getStringRectangle(){
        String string = toString();
		Rectangles container = new Rectangles();

        container.height = 1;
        container.width = string.length();
        
        return new StringRectangle(container, string);
    }
	
	public void setvalue(double a){
		absoluteValue = a;
	}
	
	public double getAbsoluteValue(){
		return absoluteValue;
	}
	
	public boolean isoperator(){
		boolean a = false;
		return a;
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
