package display;

import java.util.ArrayList;

import representTerms.StringRectangle;
import tree.Term;
import tree.compound.CompoundTerm;
import tree.downwalk;
import tree.downwalk.TreeFunction;

public class stringofrects implements TreeFunction{

	public ArrayList<Rectangles> todraw = new ArrayList<Rectangles>();
	public ArrayList<StringRectangle> a = new ArrayList<StringRectangle>();

	public ArrayList<Rectangles> listme(CompoundTerm main, String argument){

		if(argument == null){
			Rectangles rt = main.getContainer();
			int width = (int)(rt.width);
			int height = (int)(rt.height);
			Points tl = rt.getTopLeft();
			Rectangles outline = new Rectangles();

	//		outline.color = new Color(210,210,210,175);
			outline.bl.x = (int)(tl.x-2);
			outline.bl.y = (int)(tl.y-1);

			outline.width = width;
			outline.height = height;

			todraw.add(outline);}

		else if(argument == "magnet"){

			CompoundTerm top = main;

			Rectangles rt = top.getContainer();
			int width = (int)(rt.width);
			int height = (int)(rt.height);
			Points tl = rt.getTopLeft();

			Rectangles black = new Rectangles();
			Rectangles white = new Rectangles();

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

	public ArrayList<StringRectangle> writeme(CompoundTerm tr){
		a = new ArrayList<StringRectangle>();
		downwalk wk = new downwalk(tr, this);
		return a;
	}

	public void performAction(Term tr) {
		
		
		Rectangles sr = new Rectangles();
		/*sr.bl.x = tr.getContainer().bl.x;
		sr.bl.y = tr.getContainer().bl.y;
		sr.height = tr.getContainer().height;
		sr.width = tr.getContainer().width;
		
		StringRectangle StR = new StringRectangle();
		StR.container = sr;
		StR.term = tr.toString();
		StR.fontscale = tr.getScaleFactor();*//*
		if(tr.toDraw !=null){StR.todraw = tr.toDraw;}
		if(tr instanceof Divide){StR.todraw = "/";}*//*
	//	StR.container.color = tr.wordcolor;
		StR.hasParens = tr.hasParentheses();*/
	//	a.add(StR);
	}

}
