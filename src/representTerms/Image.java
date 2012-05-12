package representTerms;

import java.util.ArrayList;

import tree.Term;
import tree.compound.CompoundTerm;
//import android.graphics.Color;
//import android.util.Log;
import container.RelativeContainer;
import display.point;
import display.rectangle;
import display.stringofrects;


public class Image implements Cloneable{

	public static final String TAG = "image";
	public String st;
	public Term tr;

	public ArrayList<StringRectangle>relativeContainers  = new ArrayList<StringRectangle>();
	public ArrayList<StringRectangle>historyContainers =  new ArrayList<StringRectangle>();
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
			clone.tr = (CompoundTerm) this.tr.clone();
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



	public Image(CompoundTerm term, point bl, String argument){
		tr = term;
		bel = bl;
		arg = argument;
		st = tr.toString();
	}

	public Image(String myst, int myFont, int screenWidth, int screenHeight){
		tr = Term.getTermFromString(st);
		st = myst;
		setRelativeContainers();

		GUIMath gm = new GUIMath(5, 40, screenWidth, screenHeight);
/*		PlaceAndFont pf = gm.getPlaceAndFont(relativeContainers.get(0).container.width,
												relativeContainers.get(0).container.height, myFont);

		font = pf.font;
		bel = pf.bl;*/
	}


	public Image(String myst, point bl){
		tr = Term.getTermFromString(st);
		bel = bl;
		st = myst;
	}

	public void imagemove(CompoundTerm term, point bl, String argument){
		tr = term;
		bel = bl;
		arg = argument;

	}

	public ArrayList<StringRectangle> getRelativeContainers(){
		reset();
		return relativeContainers;
	}

	private void setRelativeContainers(){
		RelativeContainer dc = new RelativeContainer();
		//dc.drawelement(tr);
		stringofrects sp = new stringofrects();
		//relativeContainers = sp.writeme(tr);
	}

	public ArrayList<StringRectangle> getAbsoluteContainers(int myFont, point bel){
		
		reset();
		ArrayList<StringRectangle> relativeContainers = getRelativeContainers();
		ArrayList<StringRectangle> absoluteContainers = new ArrayList<StringRectangle>();
		for(int i =0; i<relativeContainers.size(); i++){
			rectangle rc = relativeContainers.get(i).container;
			StringRectangle ac = new StringRectangle();
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
		tr = Term.getTermFromString(st);
		relativeContainers  = new ArrayList<StringRectangle>();
		//		background = Color.WHITE;
		//		wordcolor = Color.BLACK;

		//bel =  new point();

		rotation = 0;
		scalefactor= 1;
		String arg = null;

		setRelativeContainers();

	}

}
