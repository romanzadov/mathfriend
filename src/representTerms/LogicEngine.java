package representTerms;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import java_specific.JavaDisplay;
import representTerms.screens.GhostScreen;
import representTerms.screens.MainScreen;
import representTerms.screens.OperatorScreen;
import display.point;


public class LogicEngine {

	//public float prefferedFont = 1;
	ArrayList<String> equations = new ArrayList<String>();
	DisplayInterface mainInterface;
	DisplayInterface ghostInterface;
	DisplayInterface operatorInterface;
	static LogicEngine LOGIC_ENGINE;

	int timerDelay = 0; 
	int timerPeriod = 100; 
	Timer timer = new Timer(); 




	private LogicEngine(String st){
		equations.add(st);
		//	timer.postDelayed(task, 1000);
	}




	public static void init(String st){

		if( LOGIC_ENGINE != null ){
			throw new RuntimeException("Can't initialize the logic engine more than once");
		}

		LOGIC_ENGINE = new LogicEngine(st);
		LOGIC_ENGINE.initGhost();
		LOGIC_ENGINE.initMain();
		LOGIC_ENGINE.initOperator();

		LOGIC_ENGINE.initTimer();


	}

	public static LogicEngine getLogicEngine(){
		if(LOGIC_ENGINE == null){
			throw new RuntimeException("The logic engine has not been constructed at the time someone tries to acceess it. ");
		}

		return LOGIC_ENGINE;
	}

	public void initTimer(){

		timer.scheduleAtFixedRate(new TimerTask() { public void run() { 
			executeDisplayLifecycle(mainInterface);
			executeDisplayLifecycle(ghostInterface);
			executeDisplayLifecycle(operatorInterface);
			updateLogic();
		}
		}, timerDelay, timerPeriod); 

	}

	private void updateLogic(){
		MainScreen mainScreen = (MainScreen) mainInterface.getAbstractScreen();
		GhostScreen ghostScreen = (GhostScreen) ghostInterface.getAbstractScreen();
		OperatorScreen operatorScreen = (OperatorScreen) operatorInterface.getAbstractScreen();


		if(mainScreen.resultTerm != null){ updateEquation(mainScreen.resultTerm); }
		if(mainScreen.moving){ operatorScreen.resetTimesTapped(); }

		if(operatorScreen.getTimesTapped() == 1){
			mainScreen.performOperationOnSel();
		}
		
		else if(operatorScreen.getTimesTapped() == 2){
		//	System.out.println("reset from logic engine");
			operatorScreen.resetTimesTapped();
			if(mainScreen.ghost != null){
				updateEquation(mainScreen.ghost.tr.toString());
			}
			
		}
		

		ghostInterface.getAbstractScreen().updateMainImage(mainScreen.ghost);
		operatorInterface.getAbstractScreen().updateMainImage(mainScreen.sel);


	}



	private void updateEquation(String nextEquation){



		Image next = new Image(nextEquation, (int)Settings.PREFFERED_FONT, 1,1);

		//change main
		MainScreen mainScreen = (MainScreen)mainInterface.getAbstractScreen();
		mainScreen.updateMainImage(next);

		//clear ghost
		GhostScreen ghostScreen = (GhostScreen)ghostInterface.getAbstractScreen();
		ghostScreen.updateMainImage(null);

		//update operator, clear timer
		OperatorScreen operatorScreen = (OperatorScreen)operatorInterface.getAbstractScreen();
		operatorScreen.updateMainImage(next);

		//update history

	}

	private void initMain(){
		String st = equations.get(0);
		Image img = new Image(st, new point(0,0));
		MainScreen ms = new MainScreen(st);
		mainInterface = new JavaDisplay(ms);
		mainInterface.updateDrawnRectangles(img.getRelativeContainers());
	}

	private void initGhost(){
		GhostScreen gs = new GhostScreen();
		ghostInterface = new JavaDisplay(gs);
	}

	private void initOperator(){
		OperatorScreen os = new OperatorScreen();
		operatorInterface = new JavaDisplay(os);
	}

	private void executeDisplayLifecycle(DisplayInterface myDisplay){

		myDisplay.getAbstractScreen().updateAbstractRectangles(myDisplay.getDrawnRectanglesScaledToIdentity());
		myDisplay.getAbstractScreen().updateTouch(myDisplay.getTouchData());
		myDisplay.getAbstractScreen().updateLogic();
		myDisplay.updateDrawnRectangles(myDisplay.getAbstractScreen().getRelativeRectangles());

	}



}
