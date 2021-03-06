package parse;


import java.util.List;

import tree.functions.Function;
import tree.simple.Constants;

public class PreSimpleTerm {

    private List<Character> characters;
    private Type type;
    private FunctionType functionType;
    private boolean leftMultiply = false;
    private boolean rightMultiply = false;
    private Constants.Constant constant;

    public static enum FunctionType {
        PLUS("+", Function.PLUS),
        MINUS("-", Function.PLUS),
        TIMES("*", Function.TIMES),
        DIVIDE("/", Function.TIMES),
        NEGATIVE("-", Function.TIMES),
        EXPONENT("^", Function.EXPONENT),
        EQUALS("=", Function.EQUALS);

        private String representation;
        private Function function;

        FunctionType(String representation, Function function) {
            this.representation = representation;
            this.function = function;
        }

        public Function getFunction() {
            return function;
        }

        public String getRepresentation() {
            return representation;
        }
    }

    public Constants.Constant getConstant() {
        return constant;
    }

    public void setConstant(Constants.Constant constant) {
        this.constant = constant;
    }

    public static enum Type {
        NUMBER,
        CONSTANT,
        FUNCTION,
        VARIABLE,
        PARENTHESES;
    }

    public PreSimpleTerm(List<Character> characters, Type type) {

        if (characters.size() == 0 || type == null) {
            throw new RuntimeException("Tried to construct a preSimpleTerm without characters or type:  characters: " + characters + "  type: " + type);
        }
        try {
            Double.parseDouble(getStringFromList(characters));
        } catch (NumberFormatException e) {
            //System.out.println("Unable to parse " + getStringFromList(characters) + " as a double.");
        }

        if(type.equals(Type.NUMBER)) {
            leftMultiply = true;
            rightMultiply = true;
        } else if(type.equals(Type.PARENTHESES)) {
            if(getStringFromList(characters).equals("(")) {
                leftMultiply = true;
            } else {
                rightMultiply = true;
            }
        } else if (type.equals(Type.CONSTANT)) {
            leftMultiply = true;
            rightMultiply = true;
        } else if (type.equals(Type.VARIABLE)) {
            leftMultiply = true;
            rightMultiply = true;
        }


        this.characters = characters;
        this.type = type;
    }

    public boolean isLeftMultiplied() {
        return leftMultiply;
    }

    public boolean isRightMultiplied() {
        return rightMultiply;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public Type getType() {
        return type;
    }

    public FunctionType getFunctionType() {
        return functionType;
    }

    public void setFunctionType(FunctionType functionType) {
        this.functionType = functionType;
    }

    private String getStringFromList(List<Character> characters) {
        String string = "";
        for (Character character : characters) {
            string = string + character;
        }
        return string;
    }

    @Override
    public String toString() {
        return getStringFromList(characters);
    }
}
