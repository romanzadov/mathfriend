package display;

import java.util.ArrayList;

import representTerms.PlaceAndFont;
import representTerms.image;
import representTerms.stringrect;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.View;

public class EquationView extends View{

	static final String TAG = "EquationView";

	public enum DisplayType{GHOST, MAIN, HISTORY}

	protected final Paint shadowRect = new Paint();	
	protected final Paint wordPaint = new Paint(); 
	protected final Paint blackRects = new Paint();
	protected final Paint whiteRect = new Paint();
	protected final Paint divideRect = new Paint();
	protected final Paint background = new Paint();

	float scale = 1;
	public DisplayType myDisplayType;
	ArrayList<String> myEquations;
	public String myEquation;
	public rectangle coverSelected = new rectangle();
	
	public image equationImage;
	public image selectedImage;

	public int prefferedFont = 30;

	public EquationView(android.content.Context context, android.util.AttributeSet attrs){
		super(context, attrs);
	}

	public void setMode(DisplayType type,ArrayList<String> equations, String equation, int backgroundColor) {

		myDisplayType = type;
		myEquations = equations;
		myEquation = equation;

		if(myDisplayType.equals(DisplayType.MAIN)){
			equationImage = new image(myEquation, prefferedFont, getWidth(), getHeight());
			selectedImage = new image(myEquation, prefferedFont, getWidth(), getHeight());
		}
		
		shadowRect.setARGB(210,210,210,175);

		wordPaint.setARGB(255, 0, 0, 0);
		wordPaint.setTextSize(prefferedFont);
		wordPaint.setAntiAlias(true);

		blackRects.setAntiAlias(true);
		blackRects.setColor(Color.BLACK);
		blackRects.setStyle(Style.STROKE);

		whiteRect.setAntiAlias(true);
		whiteRect.setColor(Color.WHITE);

		divideRect.setAntiAlias(true);
		divideRect.setColor(Color.BLACK);

		background.setColor(backgroundColor);
		
		
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
	
		if(myDisplayType.equals(DisplayType.GHOST)){
			if(myEquation != null){
				
				Log.d(TAG, "Ghost: "+equationImage);
				
				image ghost = new image(myEquation, prefferedFont, getWidth(), getHeight());
				point bl = centerEquation(ghost);
				ArrayList <stringrect> sr = ghost.getAbsoluteContainers((int)((float)prefferedFont*scale), bl);

				ArrayList<stringrect> screenPositions = new ArrayList<stringrect>();
				for(int i = 0; i<sr.size(); i++){
					rectangle container = sr.get(i).container;
					canvas.drawRect((float)container.bl.x, (float)container.bl.y-(float)container.height,
							(float)container.bl.x+(float)container.width,
							(float)container.bl.y, shadowRect);
					stringrect a = new stringrect();
					try {
						a = (stringrect)sr.get(i).clone();
					} catch (CloneNotSupportedException e) {}
					screenPositions.add(a);

				}
				ghost.tr.setScreenPositions(screenPositions);

			}

		}

		else if (myDisplayType.equals(DisplayType.MAIN)){
			if(myEquation != null){
				
				equationImage = new image(myEquation, prefferedFont, getWidth(), getHeight());
				point bl = centerEquation(equationImage);
				
				ArrayList <stringrect> sr = equationImage.getAbsoluteContainers((int)((float)prefferedFont*scale), bl);

				ArrayList<stringrect> screenPositions = new ArrayList<stringrect>();
				
				//draw box around
				rectangle container = sr.get(0).container;
				canvas.drawRect((float)container.bl.x-2, (float)container.bl.y-(float)container.height-2,
						(float)container.bl.x+(float)container.width+2,
						(float)container.bl.y+2, blackRects);
				
				//draw text
				for(int i = 0; i<sr.size(); i++){
					container = sr.get(i).container;
					

					int scaledfont = (int) sr.get(i).container.height;
					wordPaint.setTextSize(scaledfont);

					//draw divide
					if(sr.get(i).todraw=="/" ){
						rectangle a = sr.get(i).container;
						
						int top = (int)(a.bl.y-(a.height*3/4));
						int bottom = (int)(a.bl.y-(a.height/4));
						
						canvas.drawRect(a.bl.x, top, a.bl.x+a.width, bottom, divideRect);
					}
					else{
						canvas.drawText(sr.get(i).todraw, (float)(sr.get(i).container.bl.x+(prefferedFont*.2)), 
								(float)(sr.get(i).container.bl.y-(prefferedFont*.1)), wordPaint);
					}

					wordPaint.setTextSize(prefferedFont);

					
					stringrect a = new stringrect();
					try {
						a = (stringrect)sr.get(i).clone();
					} catch (CloneNotSupportedException e) {}
					screenPositions.add(a);



				}
				equationImage.tr.setScreenPositions(screenPositions);



				for(int i = 0; i<sr.size(); i++){

					//draw parentheses
					if(sr.get(i).hasParens){
						wordPaint.setTextSize((int)(sr.get(i).container.height*.8));

						canvas.drawText("(", (float)(sr.get(i).container.bl.x+(prefferedFont*.2))
								, (float)(sr.get(i).container.bl.y-(sr.get(i).container.height*.2)), wordPaint);
						canvas.drawText(")", (float)(sr.get(i).container.bl.x+sr.get(i).container.width-(prefferedFont*.6))
								, (float)(sr.get(i).container.bl.y-(sr.get(i).container.height*.2)), wordPaint);

						wordPaint.setTextSize(prefferedFont);
					}

				}

			}
			
			//draw selected
				//draw covering rectangle behind selected
			rectangle a = coverSelected;
			canvas.drawRect(a.bl.x, a.bl.y - a.height, a.bl.x+a.width, a.bl.y, background);
			
				
				// calculate selected absolute rectangles
			ArrayList<stringrect> sr = new ArrayList<stringrect>();
			if(selectedImage.tr.toString().equals(equationImage.tr.toString())){
				sr = selectedImage.getAbsoluteContainers((int)(prefferedFont*scale), centerEquation(selectedImage));
			}
			else{
				point bl = new point(selectedImage.bel.x, selectedImage.bel.y);
				sr = selectedImage.getAbsoluteContainers((int)(prefferedFont*scale), selectedImage.bel);
				selectedImage.bel = new point(bl.x, bl.y);
			}
			
				//draw the white rectangle for selected
			a = sr.get(0).container;
			canvas.drawRect(a.bl.x, a.bl.y - a.height, a.bl.x+a.width, a.bl.y, whiteRect);
			ArrayList<stringrect> screenPositions = new ArrayList<stringrect>();
			
				//draw the selected text inside the white rectangle
		

			
			for(int i = 0; i<sr.size(); i++){
				//draw fraction bar
				a = sr.get(i).container;
				if(sr.get(i).todraw=="/" ){
					a = sr.get(i).container;
					
					int top = (int)(a.bl.y-(a.height*3/4));
					int bottom = (int)(a.bl.y-(a.height/4));
					
					canvas.drawRect(a.bl.x, top, a.bl.x+a.width, bottom, divideRect);
				}
				else{ // write text
					int scaledfont = (int) sr.get(i).container.height;
					wordPaint.setTextSize(scaledfont);
					
					canvas.drawText(sr.get(i).todraw, (float)(sr.get(i).container.bl.x+(prefferedFont*.2)), 
							(float)(sr.get(i).container.bl.y-(prefferedFont*.1)), wordPaint);
				}
				//draw parens
				if(sr.get(i).hasParens){
					wordPaint.setTextSize((int)(sr.get(i).container.height*.8));

					canvas.drawText("(", (float)(sr.get(i).container.bl.x+(prefferedFont*.2))
							, (float)(sr.get(i).container.bl.y-(sr.get(i).container.height*.2)), wordPaint);
					canvas.drawText(")", (float)(sr.get(i).container.bl.x+sr.get(i).container.width-(prefferedFont*.6))
							, (float)(sr.get(i).container.bl.y-(sr.get(i).container.height*.2)), wordPaint);

					wordPaint.setTextSize(prefferedFont);
				}
				stringrect b = new stringrect();
				try {
					b = (stringrect)sr.get(i).clone();
				} catch (CloneNotSupportedException e) {}
				screenPositions.add(b);
				
			}
			
			wordPaint.setTextSize(prefferedFont);
			selectedImage.tr.setScreenPositions(screenPositions);

		}


	}


	public point centerEquation(image im){
		//centers image in your view
		point bl = new point();

		int imageHeight = (int)(prefferedFont*im.getRelativeContainers().get(0).container.height);
		int imageWidth = (int)(prefferedFont*im.getRelativeContainers().get(0).container.width);

		if(imageHeight+20>getHeight() || imageWidth>getWidth()+20){
			//resize to fit into alloted space
			//find biggest possible font
			int fonty = (int)((getHeight()-20)/im.getRelativeContainers().get(0).container.height);
			int fontx = (int)((getWidth()-20)/im.getRelativeContainers().get(0).container.width);
			int newFont = Math.min(fontx, fonty);

			imageHeight = (int)(newFont*im.getRelativeContainers().get(0).container.height);
			imageWidth = (int)(newFont*im.getRelativeContainers().get(0).container.width);

			im.tr.font = newFont;

			float newScale = (0f+newFont)/(0f+prefferedFont);
			if(newScale<scale){scale = newScale;}
		}

		bl.x = (getWidth()-imageWidth)/2;
		bl.y = (getHeight()+imageHeight)/2;


		return bl;
	}

	public point topEquation(image im){
		//puts your equation at the top of the view (this is for the history type)
		point bl = centerEquation(im);
		int imageHeight = (int)(2*bl.y - getHeight());
		bl.y = imageHeight + 10;
		return bl;
	}

}
