package droid.tests;

import ghosts.GhostImage;

import java.util.ArrayList;

import move.identify.selectterm;
import parse.path;
import representTerms.image;
import tree.term;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import display.EquationView;
import display.point;
import display.rectangle;
import display.EquationView.DisplayType;

public class DisplayThreeViews extends Activity implements OnClickListener, OnTouchListener{

	static final String TAG = "DisplayThreeViews";

	String originalEquation;
	private Vibrator myVib;

	point down;
	rectangle downSelect = new rectangle();
	boolean moved = false;
	point selsBottomLeft = new point();

	boolean inMain =false;
	boolean inGhost = false;
	boolean inHistory = false;

	EquationView mainView;
	EquationView ghostView;
	EquationView historyView;

	ArrayList<String> equationHistory;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle b = getIntent().getExtras();
		originalEquation = b.getString("st");

		//set layout and associate views with it.
		setContentView(R.layout.draw_equation);
		mainView = (EquationView)findViewById(R.id.main);
		ghostView = (EquationView)findViewById(R.id.ghost);
		historyView = (EquationView)findViewById(R.id.history);

		mainView.setOnClickListener(this);
		ghostView.setOnClickListener(this);
		historyView.setOnClickListener(this);
		
		mainView.setOnTouchListener(this);
		ghostView.setOnTouchListener(this);
		historyView.setOnTouchListener(this);

		myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

		ghostView.setMode(DisplayType.GHOST, null,null, getResources().getColor(R.color.Blue));
		mainView.setMode(DisplayType.MAIN, null, originalEquation, getResources().getColor(R.color.Green));
		historyView.setMode(DisplayType.HISTORY, null, null, getResources().getColor(R.color.Red));	

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.displayscreen_menu, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.zoom_out:
			mainView.prefferedFont = (int)(mainView.prefferedFont*.8);
			mainView.invalidate();
			return true;
		case R.id.zoom_in:
			mainView.prefferedFont = (int)(mainView.prefferedFont/.8);
			mainView.invalidate();
			return true;
		}
		return false;
	}


	public void onClick(View v){
		if(v.getId() == ghostView.getId()){
			inGhost = true;
			inMain = false;
			inHistory = false;
		}
		else if(v.getId() == mainView.getId()){
			inGhost = false;
			inMain = true;
			inHistory = false;
		}
		else if(v.getId() == historyView.getId()){
			inGhost = false;
			inMain = false;
			inHistory = true;
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event){

		int x = (int) event.getX();
		int y = (int) event.getY();
		long action = event.getAction();

		float taptime = event.getDownTime();
		long currenttime = SystemClock.uptimeMillis();
		
		if( inMain ){
			if(action == MotionEvent.ACTION_DOWN){
				down = new point(x,y);
				try {
					downSelect = (rectangle)mainView.selectedImage.tr.ScreenPosition.container.clone();
					mainView.coverSelected = downSelect;
				} catch (CloneNotSupportedException e) {}
				moved = false;
				//Log.d(TAG, "sel down: "+mainView.selectedImage.tr.ScreenPosition.container);
			}

			if(action == MotionEvent.ACTION_UP){
//				Log.d(TAG, "sel up: "+mainView.selectedImage.tr.ScreenPosition.container);
				
				downSelect = new rectangle();
				mainView.coverSelected = new rectangle();
				point mainCorner = new point(mainView.equationImage.tr.ScreenPosition.container.bl.x,
						mainView.equationImage.tr.ScreenPosition.container.bl.y);				
				
				if(!moved){
					selectterm se = new selectterm();
					term newSelect = se.whichterm(mainView.selectedImage.tr, (int)down.x, (int)down.y, mainCorner, 5);
					
				//	Log.d(TAG, "oldselect: " +mainView.selectedImage + " @: "+mainView.selectedImage.tr.ScreenPosition.container);
				//	Log.d(TAG, "newselect: "+newSelect);
					
						if(newSelect !=null){
							selsBottomLeft.x = newSelect.ScreenPosition.container.bl.x;
							selsBottomLeft.y = newSelect.ScreenPosition.container.bl.y;

						
							mainView.selectedImage = new image(newSelect, newSelect.ScreenPosition.container.bl, null );
						}
						else{
							selsBottomLeft = mainCorner;
							path pa = new path();
							term sel = pa.getTermFromString(mainView.myEquation);
							mainView.selectedImage = new image(sel, mainCorner, null );
						}

					

					myVib.vibrate(50);
				}
				
				mainView.selectedImage.bel.x = selsBottomLeft.x;
				mainView.selectedImage.bel.y = selsBottomLeft.y;

				if(moved){
			
					if(ghostView.myEquation !=null ){
						String value = ghostView.myEquation;
						equationHistory.add(0,mainView.myEquation);

					}
				}
//				Log.d(TAG, "sel up: "+mainView.selectedImage.tr.ScreenPosition.container);

			}

			if(action == MotionEvent.ACTION_MOVE){
//				Log.d(TAG, "sel move: "+mainView.selectedImage.tr.ScreenPosition.container);

				if((currenttime - taptime)>300){
					moved = true;
					mainView.selectedImage.bel.x = x + selsBottomLeft.x-down.x;
					mainView.selectedImage.bel.y = y-30;
					
					GhostImage gm = new GhostImage();
					image Ghost = (gm.newImage(mainView.equationImage, 
							mainView.selectedImage.tr, new point(event.getX(), event.getY()), down));
					Log.d(TAG, "ghost: "+Ghost);
					ghostView.equationImage = Ghost;
					ghostView.myEquation = Ghost.toString();
				}
			
			}


		}	
		mainView.invalidate();
		ghostView.invalidate();
		historyView.invalidate();

		return false;

	}


}