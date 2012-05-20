package representTerms;

import java.util.ArrayList;

import tree.Term;
import tree.compound.CompoundTerm;
//import android.graphics.Color;
//import android.util.Log;
import container.RelativeContainer;
import display.Point;
import display.Rectangle;
import display.stringofrects;


public class Image implements Cloneable {

    public static final String TAG = "image";
    public String st;
    public Term term;

    private ArrayList<StringRectangle> relativeContainers = new ArrayList<StringRectangle>();
    //	public int background = Color.WHITE;
    //	public int wordcolor = Color.BLACK;

    public Point bel = new Point();
    public int font;

    public double rotation = 0;
    public double scale = 1;

    public Image() {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Image clone = (Image) super.clone();

        if (this.term != null) {
            clone.term = (CompoundTerm) this.term.clone();
        }

        clone.bel = (Point) this.bel.clone();
        return clone;
    }

    @Override
    public String toString() {
        String st = "";
        if (term == null) {
            return null;
        }
        st += "font: " + font;
        st += " h " + getAbsoluteContainers(font, bel).get(0).container.getHeight() +
                " w " + getAbsoluteContainers(font, bel).get(0).container.getWidth() + "|";

        st += "bel: " + bel.toString() + " term: " + term.toString();
        return st;

    }

    public Image(String myst, int myFont, int screenWidth, int screenHeight) {
        term = Term.getTermFromString(myst);
        st = myst;
        setRelativeContainers();

        GUIMath gm = new GUIMath(5, 40, screenWidth, screenHeight);
/*		PlaceAndFont pf = gm.getPlaceAndFont(relativeContainers.get(0).container.width,
												relativeContainers.get(0).container.height, myFont);

		font = pf.font;
		bel = pf.bl;*/
    }


    public Image(String myst, Point bl) {
        term = Term.getTermFromString(myst);
        bel = bl;
        st = myst;
    }

    public void imagemove(CompoundTerm term, Point bl) {
        this.term = term;
        bel = bl;

    }

    public ArrayList<StringRectangle> getRelativeContainers() {
        reset();
        return relativeContainers;
    }

    private void setRelativeContainers() {
        RelativeContainer dc = new RelativeContainer();
        //dc.drawelement(tr);
        stringofrects sp = new stringofrects();
        //relativeContainers = sp.writeme(tr);
    }

    public ArrayList<StringRectangle> getAbsoluteContainers(int myFont, Point bel) {

        reset();
        ArrayList<StringRectangle> relativeContainers = getRelativeContainers();
        ArrayList<StringRectangle> absoluteContainers = new ArrayList<StringRectangle>();
        for (int i = 0; i < relativeContainers.size(); i++) {
            Rectangle rc = relativeContainers.get(i).container;
            StringRectangle ac = new StringRectangle();
            ac.container.bl.x = rc.bl.x * myFont;
            ac.container.bl.y = rc.bl.y * myFont;
            ac.container.setWidth(rc.getWidth() * myFont);
            ac.container.setHeight(rc.getHeight() * myFont);

            ac.container.bl.x = rc.bl.x * myFont + bel.x;
            ac.container.bl.y = -rc.bl.y * myFont + bel.y;

            ac.fontscale = relativeContainers.get(i).fontscale;
            ac.hasParens = relativeContainers.get(i).hasParens;
            ac.term = relativeContainers.get(i).term;
            ac.todraw = relativeContainers.get(i).todraw;

            absoluteContainers.add(ac);
        }

        return absoluteContainers;
    }

    private void reset() {
        term = Term.getTermFromString(st);
        relativeContainers = new ArrayList<StringRectangle>();
        //		background = Color.WHITE;
        //		wordcolor = Color.BLACK;

        //bel =  new point();

        rotation = 0;
        scale = 1;
        String arg = null;

        setRelativeContainers();

    }

}
