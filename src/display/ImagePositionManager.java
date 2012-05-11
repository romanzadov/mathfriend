package display;

import java.util.ArrayList;


import representTerms.Image;
import representTerms.PlaceAndFont;
import tree.CompoundTerm;

public class ImagePositionManager {

	static final String TAG = "ImagePositionManager";
	ArrayList <String> pastSteps;
	
	
	int pixelWidth ;
	int pixelHeight;
	
	rectangle screen;
	rectangle ghost;
	rectangle main;

	

	
	public ImagePositionManager(int width, int height){
		
		pixelHeight = height;
		pixelWidth = width;
	
		//I enforced "portrait" mode on DisplayScreen so I'm workng with the assumption that 
		//the width and height of my display remain constant. 
	}
	
	public point blGhost(Image myGhost, int prefferedFont){
		point bl = new point();
		//position the ghost term. and return the bottom left corner
		//The top 1/3 of the screen is for the ghost. It'll have to be resized if it is too big.
		
		//this is the amount of space that the ghost term gets. I'll buffer it by 10
		int screenHeight = pixelHeight/3 - 20;
		int screenWidth = pixelWidth - 20;
		
		int ghostHeight = (int)(prefferedFont*myGhost.getRelativeContainers().get(0).container.height);
		int ghostWidth = (int)(prefferedFont*myGhost.getRelativeContainers().get(0).container.width);

		//see if the ghostwidth and heights are too big
		if(ghostHeight>screenHeight || ghostWidth>screenWidth){
			//resize to fit into alloted space
			//find biggest possible font
			int fonty = (int)((screenHeight)/myGhost.getRelativeContainers().get(0).container.height);
			int fontx = (int)((screenWidth)/myGhost.getRelativeContainers().get(0).container.width);
			int newFont = Math.min(fontx, fonty);
			
			ghostHeight = (int)(newFont*myGhost.getRelativeContainers().get(0).container.height);
			ghostWidth = (int)(newFont*myGhost.getRelativeContainers().get(0).container.width);
			
	//		myGhost.tr.font = newFont;

		}
		
			//center and give bl
			bl.x = (screenWidth-ghostWidth)/2;
			bl.y = ghostHeight+20;
		
		
		
		return bl;
	}
	
	public PlaceAndFont blMain(Image myMain, int prefferedFont){
		//check that image fits into the display at the preffered font
		
		point bl = new point();
		float scale = 1;
		
		int screenHeight = pixelHeight*2/3;
		int screenWidth = pixelWidth;
		
		int mainHeight = (int)(prefferedFont*myMain.getRelativeContainers().get(0).container.height);
		int mainWidth = (int)(prefferedFont*myMain.getRelativeContainers().get(0).container.width);

		if(mainHeight+20>screenHeight || mainWidth+20>screenWidth){
			int fonty = (int)((screenHeight-20)/myMain.getRelativeContainers().get(0).container.height);
			int fontx = (int)((screenWidth-20)/myMain.getRelativeContainers().get(0).container.width);
			int newFont =  Math.min(fontx, fonty);
		//	Log.d(TAG, "Newfont: "+newFont + " preffont: "+prefferedFont);
			scale = (0f+newFont)/(0f+prefferedFont);
		//	Log.d(TAG, ""+myMain.scalefactor);
			mainHeight = (int)(newFont*myMain.getRelativeContainers().get(0).container.height);
			mainWidth = (int)(newFont*myMain.getRelativeContainers().get(0).container.width);
			
		}
		
		bl.x = screenWidth/2-mainWidth/2;
		bl.y = screenHeight/3+20 + mainHeight;
	//	Log.d(TAG, ""+myMain.scalefactor);

		return new PlaceAndFont(bl, scale);
	}
	
	public ArrayList<Image> getHistory(	ArrayList<String> history ,int prefferedFont, int mainBottom){
		//all the previous steps are stored in "history"
		// this method returns those images and their locations
		int historyStep = 1;
		ArrayList<Image> imageHistory = new ArrayList<Image>();
		
		for(int i = 0; i<history.size(); i++){
			//create image
			CompoundTerm tr = new CompoundTerm(history.get(i));
			Image step = new Image(tr, new point(0,0), null);
			
			//figure out new font if we have to scale it
			float scale = 1;
			int screenHeight = pixelHeight - mainBottom;
			int screenWidth = pixelWidth;
			
			int mainHeight = (int)(prefferedFont*step.getRelativeContainers().get(0).container.height);
			int mainWidth = (int)(prefferedFont*step.getRelativeContainers().get(0).container.width);

			if(mainHeight+20>screenHeight || mainWidth+20>screenWidth){
				int fonty = (int)((screenHeight-20)/step.getRelativeContainers().get(0).container.height);
				int fontx = (int)((screenWidth-20)/step.getRelativeContainers().get(0).container.width);
				int newFont =  Math.min(fontx, fonty);
				//Log.d(TAG, "Newfont: "+newFont + " preffont: "+prefferedFont);
				scale = (0f+newFont)/(0f+prefferedFont);
				//Log.d(TAG, ""+step.scalefactor);
				mainHeight = (int)(newFont*step.getRelativeContainers().get(0).container.height);
				mainWidth = (int)(newFont*step.getRelativeContainers().get(0).container.width);
				
			}
			
			//now we have the final width, height and font of our term. Time to position it.
			//it should be centered in x and a little below our main in y.
			
			point bel = new point(screenWidth/2-mainWidth/2, mainBottom+100*historyStep);
			
			step.historyContainers = step.getAbsoluteContainers((int)(prefferedFont*scale), bel);
			
			historyStep ++;
			imageHistory.add(step);
		}
		
		
		return imageHistory;
	}
}
