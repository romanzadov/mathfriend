package representTerms;

import java.io.StringReader;
import java.util.ArrayList;

import parse.path;
import tree.term;
import tree.operators.times;
//import android.graphics.Color;
//import android.util.Log;
import container.AbsoluteContainer;
import container.RelativeContainer;
import display.point;
import display.rectangle;
import display.stringofrects;


public class Image implements Cloneable{

	public static final String TAG = "image";
	public String st;
	public term tr;

	public ArrayList<stringrect>relativeContainers  = new ArrayList<stringrect>();
	public ArrayList<stringrect>historyContainers =  new ArrayList<stringrect>();
	//	public int background = Color.WHITE;
	//	public int wordcolor = Color.BLACK;

	public point bel =  new point();
	public int font;

	public double rotation =0 ;
	public double scalefactor =1;
	String arg = null;

	public Image(){}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Image clone = (Image)super.clone();

		if(this.tr != null){
			clone.tr = (term) this.tr.clone();
		}

		clone.bel = (point)this.bel.clone();
		return clone;
	}

	@Override
	public String toString(){
		String st="";
		if(tr == null ){return null;}
		st+="font: "+font;
		st+=" h "+getAbsoluteContainers(font, bel).get(0).container.height+
		" w "+getAbsoluteContainers(font, bel).get(0).container.width+"|";

		st += "bel: "+bel.toString()+" term: "+tr.toString();
		return st;

	}



	public Image(term term, point bl,  String argument){
		tr = term;
		bel = bl;
		arg = argument;
		st = tr.toString();
	}

	public Image(String myst,   int myFont, int screenWidth, int screenHeight){
		path pa = new path();
		tr = pa.getTermFromString(myst);
		st = myst;
		setRelativeContainers();

		GUIMath gm = new GUIMath(5, 40, screenWidth, screenHeight);
		PlaceAndFont pf = gm.getCenteredPlaceAndFont(relativeContainers.get(0).container.width,
												relativeContainers.get(0).container.height, myFont);

		font = pf.font;
		bel = pf.bl;
	}


	public Image(String myst, point bl){
		path pa = new path();
		tr = pa.getTermFromString(myst);
		bel = bl;
		st = myst;
	}

	public void imagemove(term term, point bl, String argument){
		tr = term;
		bel = bl;
		arg = argument;

	}

	public ArrayList<stringrect> getRelativeContainers(){
		reset();
		return relativeContainers;
	}

	private void setRelativeContainers(){
		RelativeContainer dc = new RelativeContainer();
		dc.drawelement(tr);
		stringofrects sp = new stringofrects();
		relativeContainers = sp.writeme(tr);
	}

	public ArrayList<stringrect> getAbsoluteContainers(int myFont, point bel){
		
		reset();
		ArrayList<stringrect> relativeContainers = getRelativeContainers();
		ArrayList<stringrect> absoluteContainers = new ArrayList<stringrect>();
		for(int i =0; i<relativeContainers.size(); i++){
			rectangle rc = relativeContainers.get(i).container;
			stringrect ac = new stringrect();
			ac.container.bl.x    = rc.bl.x*myFont;
			ac.container.bl.y    = rc.bl.y*myFont;
			ac.container.width   = rc.width*myFont;
			ac.container.height  = rc.height*myFont;

			ac.container.bl.x    = rc.bl.x*myFont+bel.x;
			ac.container.bl.y    = -rc.bl.y*myFont+bel.y;

			ac.fontscale = relativeContainers.get(i).fontscale;
			ac.hasParens = relativeContainers.get(i).hasParens;
			ac.term = relativeContainers.get(i).term;
			ac.todraw = relativeContainers.get(i).todraw;

			absoluteContainers.add(ac);
		}

		return absoluteContainers;
	}

	private void reset(){
		path pa = new path();
		tr = pa.getTermFromString(st);
		relativeContainers  = new ArrayList<stringrect>();
		//		background = Color.WHITE;
		//		wordcolor = Color.BLACK;

		//bel =  new point();

		rotation = 0;
		scalefactor= 1;
		String arg = null;

		setRelativeContainers();

	}

}
