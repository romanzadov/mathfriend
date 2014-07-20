package tree.functions;

public enum Function {

	EQUALS(1,2),
	PLUS(2,2),
	TIMES(3,2),
	EXPONENT(4,2);
	
	public int orderOfOperation;
	public int numberOfInputs;

	final static public String[] KNOWN_FUNCTIONS = {"+","-","*","/","^","=", "sin", "cos", "tan", "log", "ln"};
	final static public Character[] NOTFUNCTIONS = {'!', '@', '#', '$', '%', ',','~', '|'};

	private Function(int orderOfOperation, int numberOfInputs) {
		this.orderOfOperation = orderOfOperation;
		this.numberOfInputs = numberOfInputs;
	}
	
	public int getOrderOfOperation() {
		return orderOfOperation;
	}
}
