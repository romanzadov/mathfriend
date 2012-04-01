package display;

import java.util.ArrayList;

import representTerms.StringRectangle;
import tree.downwalk;
import tree.Term;
import tree.downwalk.TreeFunction;
import tree.operators.Divide;

public class stringofrects implements TreeFunction{

	public ArrayList<rectangle> todraw = new ArrayList<rectangle>();
	public ArrayList<StringRectangle> a = new ArrayList<StringRectangle>();

	public ArrayList<rectangle> listme(Term main, String argument){

		if(argument == null){
			rectangle rt = main.container;
			int width = (int)(rt.width);
			int height = (int)(rt.height);
			point tl = rt.topleft();
			rectangle outline = new rectangle();

	//		outline.color = new Color(210,210,210,175);
			outline.bl.x = (int)(tl.x-2);
			outline.bl.y = (int)(tl.y-1);

			outline.width = width;
			outline.height = height;

			todraw.add(outline);}

		else if(argument == "magnet"){

			Term top = main;

			rectangle rt = top.container;
			int width = (int)(rt.width);
			int height = (int)(rt.height);
			point tl = rt.topleft();

			rectangle black = new rectangle();
			rectangle white = new rectangle();

	//		black.color=(Color.black);
			black.bl.x = (int)(tl.x-2);
			black.bl.y = (int)(tl.y-1);
			black.width = width;
			black.height = height;

//			white.color = Color.white;
			white.bl.x = (int)(tl.x);
			white.bl.y = (int)(tl.y);
			white.width = width-3;
			white.height = height-3;

			todraw.add(black);
			todraw.add(white);
		}
		return todraw;
	}

	public ArrayList<StringRectangle> writeme(Term tr){
		a = new ArrayList<StringRectangle>();
		downwalk wk = new downwalk(tr, this);
		return a;
	}

	public void performAction(Term tr) {
		
		
		rectangle sr = new rectangle();
		sr.bl.x = tr.container.bl.x;
		sr.bl.y = tr.container.bl.y;
		sr.height = tr.container.height;
		sr.width = tr.container.width;
		
		StringRectangle StR = new StringRectangle();
		StR.container = sr;
		StR.term = tr.toString();
		StR.fontscale = tr.scaleFactor;
		if(tr.toDraw !=null){StR.todraw = tr.toDraw;}
		if(tr instanceof Divide){StR.todraw = "/";}
	//	StR.container.color = tr.wordcolor;
		StR.hasParens = tr.hasParentheses;
		a.add(StR);
	}

}
