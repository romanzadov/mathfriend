package tree.simple;


public class Variable extends SimpleTerm {
	
	public Character value;
	
	public Variable(Character a){
		value = a;
	}
	
	@Override
	public String toString(){
		return value.toString();
	}

}
