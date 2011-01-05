package droid.tests;

import java.util.ArrayList;

import parse.path;
import representTerms.PlaceAndFont;
import representTerms.Image;
import representTerms.stringrect;
import tree.Term;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.View;
import display.ImagePositionManager;
import display.point;
import display.rectangle;

public class PaintDisplayScreen extends View {

	public static final String TAG = "PaintDisplayScreen";

	ArrayList<String> history;
	
	//public Context context;
	String st;
	float xdpi;
	float ydpi;
	int width;
	int height;

	int defaultfont = 32;
	public int fontsize = 32;
	int backgroundcolor = Color.rgb(227, 204, 115);

	Term mainTerm;
	Term selTerm;
	Term ghostTerm;

	Image iMain;
	Image iSel;
	Image iGhost = null;

	ImagePositionManager myImagePositionManager;

	String pt = "no pt";
	String pt2 = "..";

	public ArrayList<Term> Steps = new ArrayList<Term>();

	public boolean fingdonw = false;

	protected final Paint wordPaint = new Paint(); 
	protected final Paint exitPaint = new Paint();
	protected final Paint blackRects = new Paint();
	protected final Paint whiteRect = new Paint();
	protected final Paint teal = new Paint();
	protected final Paint shadow = new Paint();
	protected final Paint sysPaint = new Paint();





	public PaintDisplayScreen(Context context, String str, float mxdpi,
			float mydpi,
			int mwidth,
			int mheight, 
			ArrayList<String> myHistory) {
		super(context);
		// this.setBackground(getResources().getDrawable(R.drawable.pizza));

		xdpi = mxdpi;
		ydpi = mydpi;
		width = mwidth;
		height= mheight;
		history = myHistory;

		myImagePositionManager = new ImagePositionManager(width, height);
		
		st = str;
		wordPaint.setARGB(255, 0, 0, 0);
		wordPaint.setTextSize(fontsize);
		wordPaint.setAntiAlias(true);

		sysPaint.setTextSize(14);
		sysPaint.setAntiAlias(true);

		exitPaint.setARGB(255, 81, 171, 87);
		exitPaint.setAntiAlias(true);

		blackRects.setAntiAlias(true);
		blackRects.setColor(Color.BLACK);
		blackRects.setStyle(Style.STROKE);

		whiteRect.setAntiAlias(true);
		whiteRect.setColor(Color.WHITE);

		teal.setARGB( 255, 227, 204, 172);
		teal.setAntiAlias(true);

		shadow.setARGB(210,210,210,175);
		this.setBackgroundColor(backgroundcolor);

		createImage(st);

	}


	public void createImage(String st){
		path pa = new path();
		Term tr = pa.getTermFromString(st);

	//	Log.d(TAG, ""+tr.getChilds().get(0).toString()+tr.getChilds().get(0).hasparen);

		mainTerm = tr;
		selTerm = tr;
		ghostTerm = null;



		Log.d(TAG, "mainTerm: "+mainTerm.toString());

		iMain = new Image(mainTerm.toString(), fontsize, width, height);
		iSel = new Image(selTerm.toString(), fontsize, width, height);

	}

	@Override
	protected void onDraw(Canvas canvas) {

		wordPaint.setTextSize(fontsize);
		
	//	Log.d(TAG, "iMain: "+iMain.toString());
	//	Log.d(TAG, "isel: "+iSel.toString());
		
		
		
		PlaceAndFont pas = myImagePositionManager.blMain(iMain, fontsize);
		ArrayList<stringrect> sr = iMain.getAbsoluteContainers((int)(pas.font*fontsize), pas.bl);

		ArrayList<stringrect> screenPositions = new ArrayList<stringrect>();
		for(int i = 0; i<sr.size(); i++){
			rectangle container = sr.get(i).container;
			canvas.drawRect((float)container.bl.x, (float)container.bl.y-(float)container.height,
					(float)container.bl.x+(float)container.width,
					(float)container.bl.y, shadow);
				stringrect a = new stringrect();
				try {
					a = (stringrect)sr.get(i).clone();
				} catch (CloneNotSupportedException e) {}
			screenPositions.add(a);
			
		}
		iMain.tr.setScreenPositions(screenPositions);
		//boxes around selected
		ArrayList<stringrect> selcont = null;
		if(iSel.tr.toString().equals(iMain.tr.toString())){
			selcont = iSel.getAbsoluteContainers((int)(pas.font*fontsize), pas.bl);
			}
		else{
			selcont = iSel.getAbsoluteContainers((int)(pas.font*fontsize), iSel.bel);}

		ArrayList<stringrect> selPositions = new ArrayList<stringrect>();
		for(int i = 0; i<selcont.size(); i++){
			rectangle container = selcont.get(i).container;
			canvas.drawRect((float)container.bl.x, (float)container.bl.y-(float)container.height,
					(float)container.bl.x+(float)container.width,
					(float)container.bl.y, whiteRect);
			stringrect a = new stringrect();
			try {
				a = (stringrect)selcont.get(i).clone();
			} catch (CloneNotSupportedException e) {}
			selPositions.add(a);
		}
		iSel.tr.setScreenPositions(selPositions);
	//	Log.d(TAG, "iSel sp: "+iSel.tr.toString()+" "+iSel.tr.ScreenPosition.container.bl.x);
		//text inside term
		for(int i = 0; i<sr.size(); i++){

			//draw parentheses
			if(sr.get(i).hasParens){
				wordPaint.setTextSize((int)(sr.get(i).container.height*.8));

				canvas.drawText("(", (float)(sr.get(i).container.bl.x+(fontsize*.2))
						, (float)(sr.get(i).container.bl.y-(sr.get(i).container.height*.2)), wordPaint);
				canvas.drawText(")", (float)(sr.get(i).container.bl.x+sr.get(i).container.width-(fontsize*.6))
						, (float)(sr.get(i).container.bl.y-(sr.get(i).container.height*.2)), wordPaint);

				wordPaint.setTextSize(fontsize);
			}

			//draw text
		//	Log.d(TAG, "bl x: "+sr.get(i).container.bl.x);
			int scaledfont = (int) sr.get(i).container.height;
			wordPaint.setTextSize(scaledfont);
			canvas.drawText(sr.get(i).todraw, (float)(sr.get(i).container.bl.x+(fontsize*.2)), 
					(float)(sr.get(i).container.bl.y-(fontsize*.1)), wordPaint);
			if(sr.get(i).todraw==""){
				rectangle a = sr.get(i).container;
				canvas.drawRect(a.bl.x, a.bl.y-a.height, a.bl.x+a.width, a.bl.y, blackRects);
			}

			wordPaint.setTextSize(fontsize);
		}

		//draw ghost
		
		if(iGhost!=null && iGhost.tr!=null){
	//		Log.d(TAG, "ghost: "+iGhost.toString());
			ArrayList<stringrect> ghostcont = iGhost.getAbsoluteContainers(fontsize, 
										myImagePositionManager.blGhost(iGhost, fontsize));
			Log.d(TAG, "lbGhost: "+myImagePositionManager.blGhost(iGhost, fontsize));
			for(int i = 0; i<ghostcont.size(); i++){
				canvas.drawText(ghostcont.get(i).todraw, (float)(ghostcont.get(i).container.bl.x+(fontsize*.2)), 
						(float)(ghostcont.get(i).container.bl.y-(fontsize*.1)), wordPaint);
				if(i==0){
					rectangle a = ghostcont.get(i).container;
					canvas.drawRect(a.bl.x, a.bl.y-a.height, a.bl.x+a.width, a.bl.y, whiteRect);
				}
			}
		}

		ArrayList<Image> historyImages = myImagePositionManager.getHistory(history, fontsize, 
						(int)iMain.tr.ScreenPosition.container.bl.y);
		
		for(int j = 0; j<historyImages.size(); j++){
			ArrayList<stringrect> historyContainers= historyImages.get(j).historyContainers;
			for(int i = 0; i<historyContainers.size(); i++){
				canvas.drawText(historyContainers.get(i).todraw, (float)(historyContainers.get(i).container.bl.x+(fontsize*.2)), 
						(float)(historyContainers.get(i).container.bl.y-(fontsize*.1)), wordPaint);
				if(i==0){
					rectangle a = historyContainers.get(i).container;
					canvas.drawRect(a.bl.x, a.bl.y-a.height, a.bl.x+a.width, a.bl.y, blackRects);
				}
			}
		}
	}

}
