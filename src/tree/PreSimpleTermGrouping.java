package tree;


import parse.PreSimpleTerm;

import java.util.List;

public class PreSimpleTermGrouping {

    private List<PreSimpleTerm> preSimpleTerms;
    private boolean isNegative;
    private boolean isInverse;
    private boolean hasParentheses;

    public PreSimpleTermGrouping(List<PreSimpleTerm> preSimpleTerms, boolean negative, boolean inverse, boolean hasParentheses) {
        this.preSimpleTerms = preSimpleTerms;
        isNegative = negative;
        isInverse = inverse;
        this.hasParentheses = hasParentheses;
    }

    public List<PreSimpleTerm> getPreSimpleTerms() {
        return preSimpleTerms;
    }

    public boolean isNegative() {
        return isNegative;
    }

    public boolean isInverse() {
        return isInverse;
    }

    public boolean isHasParentheses() {
        return hasParentheses;
    }
}
