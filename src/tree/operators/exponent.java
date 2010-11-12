package tree.operators;
import container.walks.fontize;
import display.rectangle;
import tree.*;
import tree.downwalk.TreeFunction;

public class exponent extends operator{
	
	public exponent()
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
	
	public rectangle giverect(term tr){
		rectangle a = new rectangle();
		float xsofar = 0;
		float ysofar = 0;
		if(tr.issimple){a=justexp(tr);}
		else{
			//check that all things are containered
			for(int i =0; i<tr.getChilds().size(); i++){
				if(tr.getChilds().get(i).container == null){
					System.out.println("error: exponent was run on non-containered terms");
				}
			}
			//remove parens if any
			for(int i =0; i<tr.getChilds().size(); i++){
				if(tr.getChilds().get(0) instanceof parens){
					tr.getChilds().remove(0);
					tr.getChilds().remove(tr.getChilds().size()-1);
				}
			}
			
			
			 xsofar = tr.getChilds().get(0).container.width;
			 ysofar = tr.getChilds().get(0).container.height;
			 
			for(int i = 2; i<tr.getChilds().size(); i+=2){
				fontize ft = new fontize(tr.getChilds().get(i), .7);
				
			}
			 
			for(int i = 2; i<tr.getChilds().size(); i+=2){
				rectangle cn = tr.getChilds().get(i).container;
			 	
				cn.bl.x = xsofar;
				cn.bl.y = ysofar;
				xsofar+=cn.width;
				ysofar+=cn.height/2;
				if(i == tr.getChilds().size()-1){
					ysofar+=cn.height/2;
				}
			}
		}
		a.width = xsofar;
		a.height = ysofar;
		return a;
	}

	public rectangle justexp(term tr){
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
	public representTerms.Image inTermMoves(representTerms.Image im, term sel,
			int IntermIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public representTerms.Image overEqualsMoves(representTerms.Image im,
			term sel, int IntermIndex, double xsel) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
