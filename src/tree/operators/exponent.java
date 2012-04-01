package tree.operators;
import container.walks.fontize;
import display.rectangle;
import tree.*;

public class Exponent extends Operator{
	
	public Exponent()
	{
		
			inputs=2;
		    invertable=true;
			commutative=false;
			distributive=false;
			associative=false;
			identity=1;
			orderofoperation=3;
			lmult = false;
			rmult = false;
		
	}

    @Override
    public Term simpleOperation(Term term) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public rectangle giverect(Term tr){
		rectangle a = new rectangle();
		float xsofar = 0;
		float ysofar = 0;
		if(tr.issimple){a=justexp(tr);}
		else{
			//check that all things are containered
			for(int i =0; i<tr.getChildren().size(); i++){
				if(tr.getChildren().get(i).container == null){
					System.out.println("error: exponent was run on non-containered terms");
				}
			}
			//remove parens if any
			for(int i =0; i<tr.getChildren().size(); i++){
				if(tr.getChildren().get(0) instanceof Parens){
					tr.getChildren().remove(0);
					tr.getChildren().remove(tr.getChildren().size()-1);
				}
			}
			
			
			 xsofar = tr.getChildren().get(0).container.width;
			 ysofar = tr.getChildren().get(0).container.height;
			 
			for(int i = 2; i<tr.getChildren().size(); i+=2){
				fontize ft = new fontize(tr.getChildren().get(i), .7);
				
			}
			 
			for(int i = 2; i<tr.getChildren().size(); i+=2){
				rectangle cn = tr.getChildren().get(i).container;
			 	
				cn.bl.x = xsofar;
				cn.bl.y = ysofar;
				xsofar+=cn.width;
				ysofar+=cn.height/2;
				if(i == tr.getChildren().size()-1){
					ysofar+=cn.height/2;
				}
			}
		}
		a.width = xsofar;
		a.height = ysofar;
		return a;
	}

	public rectangle justexp(Term tr){
		rectangle a = new rectangle();
		tr.container = a;
		return a;
	}
	
	public double function(double a, double b)
	{
		double c = Math.pow(a, b);
		return c;
	}

	@Override
	public representTerms.Image inTermMoves(representTerms.Image im, Term sel,
			int IntermIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public representTerms.Image overEqualsMoves(representTerms.Image im,
			Term sel, int IntermIndex, double xsel) {
		// TODO Auto-generated method stub
		return null;
	}
	
}