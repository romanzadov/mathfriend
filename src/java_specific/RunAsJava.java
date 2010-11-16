package java_specific;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import representTerms.Image;
import representTerms.LogicEngine;
import user.UserPrefferences;

public class RunAsJava {
	
	int width = 500;
	int height = 300;
	public static LogicEngine LOGIC_ENGINE;
	
	public static void main(String[] args) {
		String st = "2x-5=7";
		
		RunAsJava raj = new RunAsJava();
		raj.startFrame(st);
	}
	
	public void startFrame(String st){
		
		LOGIC_ENGINE = new LogicEngine();
		
		Image im = new Image(st, UserPrefferences.PREFFERED_FONT, width, height);
		System.out.println(im);
		
	
		
	}
}
