package java_specific;

import representTerms.Image;
import representTerms.LogicEngine;
import tree.Term;

public class RunAsJava {
	
	
	public static void main(String[] args) {
		String st = "2x=5+7+3-4-5-6";
		
		RunAsJava raj = new RunAsJava();
		raj.startFrame(st);
	}
	
	public void startFrame(String st){
		
		 LogicEngine.init(st);
		 LogicEngine LOGIC_ENGINE = LogicEngine.getLogicEngine();
	}
	
	
}
