package parse;

import display.rectangle;
import tree.downwalk;
import tree.term;
import tree.operators.divide;
import tree.operators.times;
import tree.simple.Number;
import tree.downwalk.TreeFunction;

public class composefractions implements TreeFunction{
	float xsofar=0;
	float ysofar=0;
	public float fractionheight = (float) 0.3;
	
	public composefractions(term tr){
		//take an upwalk through the tree
		downwalk walk = new downwalk(tr, this);
		
	}

	public void performAction(term tr) {
		//sets terms to be down or up terms in a division situtation
		if(tr.operator instanceof times || tr.operator instanceof divide){
		
			for(int i =0; i<tr.getChilds().size(); i++){
				if(tr.getChilds().get(i) instanceof divide){
					tr.getChilds().get(i+1).isbottom = true;
				}
			}
			
		}
	}
	
	
	public rectangle tofracs(term tr, int[][] places, float barheight){
		
		int stop=0;
		//find stop place
		for(int i =1; i<places.length;i++){
			if(places[i][0]==0){
				stop = i;
				break;
			}
		}
		int sofar = 0;
		//insert the * 1's 
		for (int i =0; i<stop; i++){
			
			if(places[i][0]==1000){
				Number top = new Number();
			    top = makeone(top,tr);
				times tm = new times();
				tm.issimple = true;
				rectangle cont = new rectangle();
				cont.height = 0;
				cont.width = 0;
				tm.parent = tr;
				tm.container = cont;
				tm.simples.add(tm);
				tr.getChilds().add(places[i][1]+sofar-1, tm);
				tr.getChilds().add(places[i][1]+sofar, top);
				sofar+=2;
			}
		}

		places = fracmatrix(tr);
	
		//find new stop place
		for(int i =1; i<places.length;i++){
			if(places[i][0]==0){
				stop = i;
				break;
			}
		}

		rectangle total = new rectangle();
		
		for(int i = 0; i<stop ; i++){
		
			 if(places[i][1]==0){
				rectangle mid = tr.getChilds().get(places[i][0]).container;
				mid.bl.x = xsofar;
				if(barheight!=0){
				mid.bl.y = barheight - mid.height/2;
				}
				xsofar +=mid.width;

				if(mid.height>ysofar){ysofar = mid.height;}
			}
			else{
				term top = tr.getChilds().get(places[i][0]);
				term bar = tr.getChilds().get(places[i][0]+1);
				term bottom = 	tr.getChilds().get(places[i][0]+2);
				total = properfraction(top, bar, bottom, barheight);
			}
		}
		
		total.height = ysofar;
		total.width = xsofar;
		return total;
		
	}
	
	public rectangle properfraction(term top, term bar, term bottom, float barheight){
		rectangle a = new rectangle();
		
		float maxwidth = Math.max(top.container.width, bottom.container.width);
		
		bottom.container.bl.y = barheight - bottom.container.height;
		bottom.container.bl.x = xsofar + maxwidth/2 - bottom.container.width/2;
		bar.container.bl.x = xsofar;
		bar.container.width = maxwidth;
		bar.container.height = fractionheight;
		top.container.bl.y = barheight + fractionheight;
		top.container.bl.x = xsofar + maxwidth/2-top.container.width/2;
		bar.container.bl.y = barheight;
					
		xsofar += maxwidth;
		float h = top.container.height+top.container.bl.y;
		a.width = maxwidth;
		a.height = h;
		if(h>ysofar){ysofar = h;}
		return a; 
	}
	
	public Number makeone(Number tr, term term){
		Number num = new Number();
		num.parent = term;
		num.issimple = true;
		num.value = 1;
		num.todraw = "1";
		rectangle cont = new rectangle();
		cont.height = 1;
		cont.width = 1;
		cont.bl.x = 0;
		cont.bl.y =0;
		num.container = cont;
		num.simples.add(num);
		tr = num;
		return tr;
	}
	
	public int[][] fracmatrix(term tr){

		int[][] places = new int[tr.getChilds().size()][2];
		int place = 0;
		//first, break up into fractions
		int[] thisfrac = new int[2];

		for(int i=0; i<tr.getChilds().size();i++){

			if(!tr.getChilds().get(i).issimple||
					!(tr.getChilds().get(i) instanceof times)
					&&!(tr.getChilds().get(i) instanceof divide)){
				int side = place/2;
				int top = place%2;
				places[side][top] = i;
				//stick normal terms where they fall
			}

			else if(tr.getChilds().get(i) instanceof times){
				if(place%2==0){place = place+2;}
				if(place%2==1){place++; }
			}
			else if(tr.getChilds().get(i) instanceof divide){
				if(place%2==0){place++;}
				else if(place%2==1){
					//1000 is code for 1/x
					place++;
					int side = place/2;
					int top = place%2;
					places[side][top]=1000;
					place++;
					side = place/2;
					top = place%2;
					places[side][top]=i;
				}
			}

		}
	return places;
	}
}