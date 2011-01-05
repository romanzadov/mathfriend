package representTerms.screens;

import ghosts.GhostImage;

import java.util.ArrayList;

import move.identify.TermMath;
import move.identify.selectterm;
import representTerms.Image;
import representTerms.Settings;
import representTerms.TouchData;
import representTerms.stringrect;
import representTerms.Settings.ScreenType;
import representTerms.TouchData.TouchType;
import representTerms.stringrect.type;
import tree.Term;
import tree.simple.Number;
import display.point;
import display.rectangle;

public class MainScreen implements AbstractedScreen{


	Image main;
	public Image sel;
	public Image ghost;
	point down = new point();
	double taptime = 0;
	boolean moved = false;
	boolean moving = false;
	point selsBottomLeft = new point();
	ArrayList<stringrect> drawn = new ArrayList<stringrect>();
	ArrayList<TouchData> touches = new ArrayList<TouchData>();
	public String resultTerm = null;
	
	public MainScreen(String st){
		main = new Image(st, Settings.PREFFERED_FONT, 1, 1);
		sel = main;
	}

	@Override 
	public ScreenType getScreenType(){
		return ScreenType.MAIN;
	}
	
	public void performOperationOnSel(){
		
		Image mainClone = null;
		
		Term compound = sel.tr.getResultOfOperation();
		System.out.println("compound: "+compound);
		if(compound != null) {
			try {
				mainClone = (Image) main.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<Integer> key = TermMath.findTreePositionOfSelected(main.tr, sel.tr);
			Term selClone = TermMath.findTermUsingKey(mainClone.tr, key);
			
			
			Term parentHolder = selClone.parent;
			compound.parent = parentHolder;
			int selPlace = parentHolder.getChilds().indexOf(selClone);
			parentHolder.getChilds().set(selPlace, compound);
			
		}
		
		if(mainClone != null){
			ghost = new Image(mainClone.tr.toString(), Settings.PREFFERED_FONT, 1,1);
		}
		
	}
	
	@Override
	public int getBackgroundColor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<stringrect> getRelativeRectangles() {
		//this will give a list of rectangles to draw on our display screen. It is sent to the display interface.
		
		
		//first, we draw the main equation 
		ArrayList <stringrect> relatives = main.getRelativeContainers();
		for(int i = 0; i< relatives.size(); i++){
			relatives.get(i).myType =  type.MAIN;
		}
		
		//second, find the selected term among them, and tag it as SELECTED
		int position = main.tr.positionOfInnerTermDown(sel.tr);
		if(position >= 0 && position<relatives.size()){
			relatives.get(position).myType = type.SELECTED;
		}
		
		// if the selected term is moving, we will cover its space with another rectangle, and then draw it somewhere outside the equation.
		if(moving){
			if(sel!=null){
				
				point scaled = scaleIdealPointToRelativeOne(sel.bel, relatives.get(0).container, drawn.get(0).container);
				
				
				
				try {
					stringrect selectedRect = (stringrect) relatives.get(position).clone();
					
					stringrect cover = (stringrect) relatives.get(position).clone();
					cover.myType = type.SELECT_COVER;
					relatives.add(cover);
				
					
				} catch (CloneNotSupportedException e) {}
				
				ArrayList<stringrect> sels = sel.getRelativeContainers();
				
				for(int i = 0; i<sels.size(); i++){
					sels.get(i).container.bl.x += scaled.x-relatives.get(position).container.width/2;
					sels.get(i).container.bl.y += scaled.y;
					sels.get(i).myType = type.SELECTED;
					relatives.add(sels.get(i));
				}
				
			}
		}
		else{

		}
		
		return relatives;

	}


	@Override
	public void updateTouch(ArrayList<TouchData> touch){
		touches.addAll(touch);
		updateLogic();
	}

	@Override
	public void updateLogic(){
		for(int i = 0; i<touches.size(); i++){
			singleTouch(touches.get(0));
			touches.remove(0);
		}
		
	
	}

	private void singleTouch(TouchData touch) {

		float x = touch.position.x;
		float y = touch.position.y;
		TouchType touchType = touch.myType;
		//System.out.println(""+touch);

		long currenttime = System.currentTimeMillis();


		if(touchType == TouchType.PRESSED){
			down = new point(x,y);
			taptime = touch.time;
			moved = false;
		}
		if(touchType == TouchType.RELEASED){

			if(!moved){

				selectterm se = new selectterm();
				Term newSelect = se.whichterm(sel.tr, x, y, main.tr.ScreenPosition.container.bl, 0);

				if(newSelect !=null){

					selsBottomLeft.x = newSelect.ScreenPosition.container.bl.x;
					selsBottomLeft.y = newSelect.ScreenPosition.container.bl.y;

					sel = new Image(newSelect, selsBottomLeft, null );
				}
				else{
					selsBottomLeft.x = main.tr.ScreenPosition.container.bl.x;
					selsBottomLeft.y = main.tr.ScreenPosition.container.bl.y;

					sel = main;
				}

				sel.bel.x = selsBottomLeft.x;
				sel.bel.y = selsBottomLeft.y;




			}

			if(moved){

				//get the ghost term. If null, do nothing. If not null, indicate to the logicEngine to make switch to the next equation.
				
				if(ghost != null){
					resultTerm = ghost.tr.toString();
				}
				
			}
			moving = false;
		}

		if(touchType == TouchType.DRAGGED){

			moving = true;

			if((currenttime - taptime)>Settings.SELECTION_TIME){
				moved = true;
				sel.bel.x = x;
				sel.bel.y = y;

				GhostImage gm = new GhostImage();
				//make a new ghost based on existing term positions.
				ghost = gm.newImage(main, sel.tr, new point (x, y), down);
			
					if(ghost != null){
						ghost.font = Settings.PREFFERED_FONT;
					}
					if(ghost != null && ghost.tr == null){
						ghost = null;
					}

			}
		}


		if(sel != null){
			//System.out.println("sel: "+sel.tr);
		}
	}

	@Override
	public void updateAbstractRectangles(ArrayList<stringrect> drawn) {
		this.drawn = drawn;
		main.tr.setScreenPositions(drawn);
	}

	public point scaleIdealPointToRelativeOne(point a, rectangle relativeContainer, rectangle idealContainer){
		
		point b = new point();
		
		b.x = (a.x-idealContainer.bl.x)*relativeContainer.width/idealContainer.width;
		b.y = (a.y - idealContainer.bl.y)*relativeContainer.height/idealContainer.height;
		
		return b;
	}

	@Override
	public void updateMainImage(Image main) {
		this.main = main;
		sel = main;
		touches.clear();
		drawn.clear();
		ghost = null;
		down = new point();
		taptime = 0;
		moved = false;
		moving = false;
		selsBottomLeft = new point();
		resultTerm = null;
		
	}
	
}
