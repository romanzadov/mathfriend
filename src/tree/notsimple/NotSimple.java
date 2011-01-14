package tree.notsimple;

import parse.path;
import tree.Term;
import tree.simple.simpleterm;

public abstract class NotSimple extends Term{

	public abstract boolean canConstruct(Term tr);
		
}
