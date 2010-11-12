package droid.tests;

import java.util.ArrayList;

import ghosts.GhostImage;
import move.identify.selectterm;
import representTerms.Image;
import tree.term;
import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import display.point;

public class DisplayScreen extends Activity{

	static final String TAG = "DisplayScreen";
	String value;
	public PaintDisplayScreen a;
	private Vibrator myVib;

	//the menu bar is size 50, so we have to subtract that out when geting y coords;
	int ybar = 50;
	point down;
	boolean moved = false;
	point selsBottomLeft = new point();
	public int widthPixels;
	public int heightPixels;
	
	ArrayList<String> history = new ArrayList<String>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle b = getIntent().getExtras();
		value = b.getString("st");
		setContentView(R.layout.draw_equation);
		myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		widthPixels = metrics.widthPixels;
		heightPixels = metrics.heightPixels;
		
		a = new PaintDisplayScreen(this, value,metrics.xdpi,metrics.ydpi
				, metrics.widthPixels, metrics.heightPixels, history);
		
		setContentView(a);
		


	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.displayscreen_menu, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.zoom_out:
			a.fontsize = (int)(a.fontsize*.8);
			a.invalidate();
			return true;
		case R.id.zoom_in:
			a.fontsize = (int)(a.fontsize/.8);
			a.invalidate();
			return true;
		}
		return false;
	}

	public boolean onTouchEvent(MotionEvent event){

		int x = (int) event.getX();
		int y = (int) event.getY() - ybar;
		long action = event.getAction();

		float taptime = event.getDownTime();
		long currenttime = SystemClock.uptimeMillis();

		
		if(action == MotionEvent.ACTION_DOWN){
			down = new point(x,y);
			moved = false;
		}
		if(action == MotionEvent.ACTION_UP){

			if(!moved){
				
				Log.d(TAG, "iSel: "+a.iSel.toString()+" isel SP: "+a.iSel.tr.ScreenPosition.container.bl.x+","+a.iSel.tr.ScreenPosition.container.bl.y);
				selectterm se = new selectterm();
				term newSelect = se.whichterm(a.iSel.tr, (int)down.x, (int)down.y, a.iMain.bel, 5);
				try {
					if(newSelect !=null){
						selsBottomLeft.x = newSelect.ScreenPosition.container.bl.x;
						selsBottomLeft.y = newSelect.ScreenPosition.container.bl.y;

						a.selTerm = (term)newSelect.clone();
						a.iSel = new Image(a.selTerm, a.selTerm.container.bl, null );
					}
					else{
						selsBottomLeft.x = a.iMain.bel.x;
						selsBottomLeft.y = a.iMain.bel.y;
						
						a.selTerm = (term)a.mainTerm.clone();
						a.iSel = new Image(a.selTerm, a.iMain.bel, null );
					}

				} catch (CloneNotSupportedException e) {}
				
				myVib.vibrate(50);
			}
			a.iSel.bel.x = selsBottomLeft.x;
			a.iSel.bel.y = selsBottomLeft.y;
			
			if(moved){
				if(a.iGhost !=null && a.iGhost.tr != null ){
					value = a.iGhost.tr.toString();
					history.add(0,a.iMain.tr.toString());
					DisplayMetrics metrics = new DisplayMetrics();
					getWindowManager().getDefaultDisplay().getMetrics(metrics);

					widthPixels = metrics.widthPixels;
					heightPixels = metrics.heightPixels;
					
					a = new PaintDisplayScreen(this, value,metrics.xdpi,metrics.ydpi
							, metrics.widthPixels, metrics.heightPixels, history);
					
					setContentView(a);
				}
			}
			a.invalidate();
		}

		if(action == MotionEvent.ACTION_MOVE){
		
			if((currenttime - taptime)>300){
				moved = true;
				a.fingdonw = true;
				a.iSel.bel.x = x + selsBottomLeft.x-down.x;
				a.iSel.bel.y = y-30;
				
				GhostImage gm = new GhostImage();
				a.iGhost = (gm.newImage(a.iMain, a.iSel.tr, new point(event.getX(), event.getY()), down));
				if(a.ghostTerm !=null){
				Log.d(TAG, a.ghostTerm.toString());}
				else{Log.d(TAG, "ghost null");}
				a.invalidate();
			}
		}
		
		

		return false;

	}


}