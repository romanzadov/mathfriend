package tree;


import tree.simple.SimpleTerm;

public abstract class Term implements Cloneable{

    private boolean hasParentheses;
	private boolean isNegative = false;
    private boolean isInverse = false;

    public boolean hasParentheses() {
        return hasParentheses;
    }

    public void setHasParentheses(boolean hasParentheses) {
        this.hasParentheses = hasParentheses;
    }

    public boolean isNegative() {
        return isNegative;
    }

    public void setNegative(boolean negative) {
        isNegative = negative;
    }

    public boolean isInverse() {
        return isInverse;
    }

    public void setInverse(boolean inverse) {
        isInverse = inverse;
    }

    public boolean isSimple() {
        if (this instanceof SimpleTerm) {
            return true;
        }
        return false;
    }
}
