package tree.functions;


public enum Function {

	EQUALS(1),
	PLUS(2),
	TIMES(3),
	EXPONENT(4);
	
	public int orderOfOperation;

	final static public String[] KNOWN_FUNCTIONS = {"+","-","*","/","^","=", "sin", "cos", "tan", "log", "ln"};
	final static public Character[] NOTFUNCTIONS = {'!', '@', '#', '$', '%', ',','~', '|'};

	private Function(int orderOfOperation) {
		this.orderOfOperation = orderOfOperation;
	}
	
	public int getOrderOfOperation() {
		return orderOfOperation;
	}
}
